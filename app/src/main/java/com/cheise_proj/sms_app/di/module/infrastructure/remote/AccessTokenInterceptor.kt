package com.cheise_proj.sms_app.di.module.infrastructure.remote


import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.repository.TokenRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class AccessTokenInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) :
    Interceptor {
    companion object {
        private const val AUTHENTICATION_HEADER = "Authorization"
        private const val TOKEN_TYPE = "Bearer"
    }


    override fun intercept(chain: Interceptor.Chain): Response {

        val user = getUserData()
        val accessToken = user.accessToken ?: return chain.proceed(chain.request())

        val request = newRequestWithAccessToken(chain.request(), accessToken)
        return chain.proceed(request)
    }

    private fun getUserData(): User {
        return Single.fromObservable(tokenRepository.getUser()).onErrorReturn {
            User()
        }.blockingGet()
    }

    private fun newRequestWithAccessToken(
        request: Request,
        accessToken: String,
        tokenType: String? = TOKEN_TYPE
    ): Request {
        return request.newBuilder()
            .header(AUTHENTICATION_HEADER, "$tokenType $accessToken")
            .build()
    }
}


