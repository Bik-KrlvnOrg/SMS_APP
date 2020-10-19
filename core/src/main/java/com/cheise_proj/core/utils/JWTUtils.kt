package com.cheise_proj.core.utils

import com.auth0.android.jwt.JWT
import com.cheise_proj.core.common.AuthType
import com.cheise_proj.core.domain.model.User

object JWTUtils {
    fun decodeBody(token:String){
        val jwt = JWT(token)
        val claims = jwt.claims
        val user = User()
        user.username = claims["username"]?.asString()
        user.type = AuthType.valueOf(claims["type"].toString())
        user.exp = jwt.expiresAt?.time
        user.iat = jwt.issuedAt?.time
    }
}