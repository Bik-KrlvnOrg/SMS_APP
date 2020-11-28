package com.cheise_proj.sms_app.di.module.remote

import com.cheise_proj.core.data.local.entity.UserEntity
import com.cheise_proj.core.domain.repository.TokenRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import javax.inject.Inject


class AccessTokenAuthenticator @Inject constructor(private val tokenRepository: TokenRepository) :
    Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val user = getUserData()
        val accessToken = user?.accessToken
        val refreshToken = user?.refreshToken

        if (!isRequestWithAccessToken(response) || accessToken == null) {
            return null
        }
        synchronized(this) {
            val newAccessToken = getUserData()?.accessToken
            // Access token is refreshed in another thread.
            if (accessToken != newAccessToken) {
                return newRequestWithAccessToken(response.request, newAccessToken)
            }

            // Need to refresh an access token
            val data = getNewToken(user.username, accessToken, refreshToken)
            val updatedAccessToken = data?.get("accessToken")
            return newRequestWithAccessToken(response.request, updatedAccessToken);
        }
    }

    private fun isRequestWithAccessToken(response: Response): Boolean {
        val header = response.request.header("Authorization")
        return header != null && header.startsWith("Bearer")
    }

    private fun getUserData(): UserEntity? {
        var user: UserEntity? = null
        runBlocking {
            val job = async { tokenRepository.getUser() }
            runBlocking { user = job.await() }
        }
        Timber.i("user-accessToken: ${user?.accessToken}")
        Timber.i("user-refreshToken: ${user?.refreshToken}")
        return user
    }

    private fun getNewToken(
        username: String?,
        accessToken: String?,
        refreshToken: String?
    ): HashMap<String, String>? {
        var map: HashMap<String, String>? = HashMap()
        runBlocking {
            val job = async { tokenRepository.refreshToken(username, accessToken, refreshToken) }
            runBlocking {
                map = job.await()
            }
        }
        Timber.i("new tokens: $map")
        return map
    }


    private fun newRequestWithAccessToken(request: Request, accessToken: String?): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}