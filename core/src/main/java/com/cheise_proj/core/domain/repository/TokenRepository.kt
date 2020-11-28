package com.cheise_proj.core.domain.repository

import com.cheise_proj.core.data.local.entity.UserEntity

interface TokenRepository {
    suspend fun getUser(): UserEntity?
    suspend fun refreshToken(
        username:String?,
        accessToken: String?,
        refreshToken: String?
    ): HashMap<String, String>?
}