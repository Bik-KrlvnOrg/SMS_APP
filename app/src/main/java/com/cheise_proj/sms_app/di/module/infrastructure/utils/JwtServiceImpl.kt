package com.cheise_proj.sms_app.di.module.infrastructure.utils

import com.auth0.android.jwt.JWT
import com.cheise_proj.domain.model.User
import com.cheise_proj.infrastructure.utils.JwtService
import timber.log.Timber
import javax.inject.Inject

class JwtServiceImpl @Inject constructor() : JwtService {
    override fun decodeBody(token: String): User {
        val jwt = JWT(token)
        val claims = jwt.claims
        Timber.i("jwt_claims: ${claims.entries}")
        val user = User()
        user.id = claims["id"]?.asInt() ?: 0
        user.username = claims["username"]?.asString()
        user.type = claims["type"]?.asString()
        user.exp = jwt.expiresAt?.time
        user.iat = jwt.issuedAt?.time
        Timber.i("jwt_user: $user")
        return user
    }
}