package com.cheise_proj.core.domain.repository

import com.cheise_proj.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun authenticateUser(username: String, password: String, type: String): Flow<User>
    fun logout()
}