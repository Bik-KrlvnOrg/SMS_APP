package com.cheise_proj.sms_app.di.module.remote

import com.cheise_proj.core.data.local.entity.UserEntity
import com.cheise_proj.core.domain.repository.TokenRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject


class AccessTokenInterceptor @Inject constructor(private val tokenRepository: TokenRepository) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val user = getUserData()
        val accessToken = user?.accessToken ?: return chain.proceed(chain.request())

        val request = newRequestWithAccessToken(chain.request(), accessToken)
        return chain.proceed(request)
    }

    private fun getUserData(): UserEntity? {
        var user: UserEntity? = null
        runBlocking {
            val job = async { tokenRepository.getUser() }
            runBlocking { user = job.await() }
        }
        Timber.i("current-accessToken: ${user?.accessToken}")
        return user
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}


