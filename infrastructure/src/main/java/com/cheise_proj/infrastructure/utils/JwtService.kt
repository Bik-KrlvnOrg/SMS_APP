package com.cheise_proj.infrastructure.utils

import com.cheise_proj.domain.model.User

interface JwtService {
    fun decodeBody(token: String): User
}