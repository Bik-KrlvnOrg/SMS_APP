package com.cheise_proj.core.data.repository

import android.text.TextUtils
import com.cheise_proj.core.data.local.dao.UserDao
import com.cheise_proj.core.data.local.entity.UserEntity
import com.cheise_proj.core.data.remote.service.ApiService
import com.cheise_proj.core.domain.repository.TokenRepository
import dagger.Lazy
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val apiService: Lazy<ApiService>,
    private val userDao: UserDao
) : TokenRepository {
    override suspend fun getUser(): UserEntity? {
        return userDao.getUser().first()
    }

    override suspend fun refreshToken(
        username: String?,
        accessToken: String?,
        refreshToken: String?
    ): HashMap<String, String>? {
        if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(refreshToken)) return null
        val hashMap = HashMap<String, String>()
        hashMap["oldAccessToken"] = accessToken ?: ""
        hashMap["refreshToken"] = refreshToken ?: ""
        val response = apiService.get().refreshToken(hashMap)

        Timber.i("token refreshed: :$response")
        Timber.i("token-insert: :$username \n accessToken: ${response.accessToken} \n refToken: ${response.refreshToken}")

        userDao.updateTokens(username, response.accessToken, response.refreshToken)

        hashMap["accessToken"] = response.accessToken
        hashMap["refreshToken"] = response.refreshToken

        return hashMap
    }

}