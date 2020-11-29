package com.cheise_proj.infrastructure.repository

import com.cheise_proj.domain.model.Credential
import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.repository.AuthenticationRepository
import com.cheise_proj.infrastructure.local.dao.UserDao
import com.cheise_proj.infrastructure.local.mapper.mapFrom
import com.cheise_proj.infrastructure.local.mapper.mapTo
import com.cheise_proj.infrastructure.remote.mapper.mapTo
import com.cheise_proj.infrastructure.remote.service.ApiService
import com.cheise_proj.infrastructure.utils.JwtService
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.Single
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val jwtService: JwtService
) : AuthenticationRepository {
    companion object {
        private const val USER_ERROR = "user not found"
    }

    override fun authenticateUser(
        username: String,
        password: String,
        type: String
    ): Observable<User> {
        return apiService.login(
            Credential(
                username = username,
                password = password,
                type = type
            ).mapTo()
        ).map {
            Timber.i("auth_dto: $it")
            val decodeBody = jwtService.decodeBody(it.accessToken)
            val entity = decodeBody.mapTo()
            userDao.saveUser(entity)
            decodeBody
        }
    }

    override fun getUser(): Observable<User?> {
        return RxJavaBridge.toV3Observable(
            userDao.getUser()
                .map {
                    it.type = it.type.toUpperCase(Locale.getDefault())
                    it
                }
                .map { it.mapFrom() }
                .onErrorResumeNext { Single.error(Throwable(USER_ERROR)) }
                .toObservable()
        )
    }

    override fun logout() {

    }
}