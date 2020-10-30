package com.cheise_proj.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository<T> {
    fun getProfile(): Flow<T>
}