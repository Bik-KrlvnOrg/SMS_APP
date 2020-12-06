package com.cheise_proj.infrastructure.utils

import com.cheise_proj.domain.model.User
import com.cheise_proj.infrastructure.local.entity.UserEntity
import com.cheise_proj.infrastructure.remote.model.dto.LoginDto
import com.cheise_proj.infrastructure.remote.model.dto.TokenDto

object FakeAuth {
    fun getUser(): User {
        return User(
            id = 1,
            username = "any username",
            accessToken = null,
            exp = null,
            iat = null,
            refreshToken = null,
            type = "any type"

        )
    }

    fun getUserLocal(): UserEntity {
        return UserEntity(
            id = 1,
            username = "any username",
            exp = null,
            iat = null,
            type = "any type",
            refreshToken = "any refresh token",
            accessToken = "any access token"
        )
    }

    fun getLoginDto(): LoginDto {
        return LoginDto(
            accessToken = "any access token",
            refreshToken = "any refresh token",
            tokenType = "any token type"
        )
    }

    fun getTokenDto():TokenDto{
        return TokenDto(
            accessToken = "any access token",
            refreshToken = "any refresh token",
            tokenType = "bearer"
        )
    }

}