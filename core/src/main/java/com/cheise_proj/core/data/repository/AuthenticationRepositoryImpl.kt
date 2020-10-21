package com.cheise_proj.core.data.repository

import com.cheise_proj.core.data.local.dao.UserDao
import com.cheise_proj.core.data.local.mapper.mapTo
import com.cheise_proj.core.data.remote.mapper.mapTo
import com.cheise_proj.core.data.remote.service.ApiService
import com.cheise_proj.core.domain.model.Credential
import com.cheise_proj.core.domain.model.User
import com.cheise_proj.core.domain.repository.AuthenticationRepository
import com.cheise_proj.core.utils.JWTUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) :
    AuthenticationRepository {

    override fun authenticateUser(username: String, password: String, type: String): Flow<User> {
        return flow {
            val data = apiService.login(
                Credential(
                    username = username,
                    password = password,
                    type = type
                ).mapTo()
            )
            Timber.i("auth_dto: $data")
            val decodedUser = JWTUtils.decodeBody(data.accessToken)
            val entity = decodedUser.mapTo().apply {
                accessToken = data.accessToken
                refreshToken = data.refreshToken
            }
            userDao.saveUser(entity)
            Timber.i("decoded_user: $decodedUser")
            emit(decodedUser)
        }
    }

    override fun logout() {
    }
}