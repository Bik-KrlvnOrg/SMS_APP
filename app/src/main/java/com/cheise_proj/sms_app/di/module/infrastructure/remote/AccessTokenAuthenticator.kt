package com.cheise_proj.sms_app.di.module.infrastructure.remote


import com.cheise_proj.domain.model.Token
import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.repository.TokenRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class AccessTokenAuthenticator @Inject constructor(
    private val tokenRepository: TokenRepository
) :
    Authenticator {
    companion object {
        private const val AUTHENTICATION_HEADER = "Authorization"
        private const val TOKEN_TYPE = "Bearer"
    }


    override fun authenticate(route: Route?, response: Response): Request? {
        val user = getUserData()
        val accessToken = user.accessToken
        val refreshToken = user.refreshToken

        if (!isRequestWithAccessToken(response) || accessToken == null) {
            return null
        }
        synchronized(this) {
            val newAccessToken = getUserData().accessToken
            // Access token is refreshed in another thread.
            if (accessToken != newAccessToken) {
                return newRequestWithAccessToken(response.request, newAccessToken)
            }

            // Need to refresh an access token
            val data = getNewToken(user.username!!, accessToken, refreshToken!!)
            val updatedAccessToken = data.accessToken
            return newRequestWithAccessToken(response.request, updatedAccessToken);
        }
    }

    private fun isRequestWithAccessToken(response: Response): Boolean {
        val header = response.request.header(AUTHENTICATION_HEADER)
        return header != null && header.startsWith(TOKEN_TYPE)
    }

    private fun getUserData(): User {
        return Single.fromObservable(
            tokenRepository.getUser()
        ).onErrorReturn { User() }.blockingGet()
    }

    private fun getNewToken(
        username: String,
        accessToken: String,
        refreshToken: String
    ): Token {
        return Single.fromObservable(
            tokenRepository.refreshToken(username, accessToken, refreshToken)
        ).onErrorReturn { Token() }.blockingGet()
    }


    private fun newRequestWithAccessToken(
        request: Request,
        accessToken: String?,
        tokenType: String? = TOKEN_TYPE
    ): Request {
        return request.newBuilder()
            .header(AUTHENTICATION_HEADER, "$tokenType $accessToken")
            .build()
    }
}