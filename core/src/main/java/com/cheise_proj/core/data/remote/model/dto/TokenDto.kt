package com.cheise_proj.core.data.remote.model.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
)