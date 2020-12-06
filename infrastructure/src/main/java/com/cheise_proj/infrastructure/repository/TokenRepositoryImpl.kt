package com.cheise_proj.infrastructure.repository

import com.cheise_proj.domain.model.Token
import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.repository.TokenRepository
import com.cheise_proj.infrastructure.local.dao.UserDao
import com.cheise_proj.infrastructure.local.mapper.mapFrom
import com.cheise_proj.infrastructure.remote.mapper.mapTo
import com.cheise_proj.infrastructure.remote.service.ApiService
import dagger.Lazy
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val apiService: Lazy<ApiService>,
    private val userDao: UserDao
) : TokenRepository {
    companion object {
        private const val ACCESS_TOKEN_KEY = "oldAccessToken"
        private const val REFRESH_TOKEN_KEY = "refreshToken"
    }

    override fun getUser(): Observable<User> {
        return RxJavaBridge.toV3Observable(
            userDao.getUser()
                .map { it.mapFrom() }
                .toObservable())
    }

    override fun refreshToken(
        username: String,
        accessToken: String,
        refreshToken: String
    ): Observable<Token> {
        if (accessToken.isEmpty() || refreshToken.isEmpty()) return Observable.empty()
        val hashMap = hashMapOf(ACCESS_TOKEN_KEY to accessToken, REFRESH_TOKEN_KEY to refreshToken)

        return apiService.get().refreshToken(hashMap)
            .map {
                Timber.i("update token: $it")
                userDao.updateTokens(username, it.accessToken, it.refreshToken)
                it.mapTo()
            }
    }

}