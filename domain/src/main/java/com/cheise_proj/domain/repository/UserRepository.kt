package com.cheise_proj.domain.repository

import io.reactivex.rxjava3.core.Observable

interface UserRepository<T> {
    fun getProfile(): Observable<T?>
}