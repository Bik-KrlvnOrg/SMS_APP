package com.cheise_proj.domain.repository

import com.cheise_proj.domain.model.User
import io.reactivex.rxjava3.core.Observable

interface AuthenticationRepository {
    fun authenticateUser(username: String, password: String, type: String): Observable<User>
    fun getUser(): Observable<User?>
    fun logout()
}