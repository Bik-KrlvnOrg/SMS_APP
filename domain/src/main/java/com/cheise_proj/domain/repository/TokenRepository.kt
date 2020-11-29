package com.cheise_proj.domain.repository

import com.cheise_proj.domain.model.Token
import com.cheise_proj.domain.model.User
import io.reactivex.rxjava3.core.Observable

interface TokenRepository {
    fun getUser(): Observable<User>
    fun refreshToken(
        username: String,
        accessToken: String,
        refreshToken: String
    ): Observable<Token>
}