package com.cheise_proj.sms_app.di.module.repository

import com.cheise_proj.core.data.repository.AuthenticationRepositoryImpl
import com.cheise_proj.core.domain.repository.AuthenticationRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.Binders::class])
class RepositoryModule {
    @Module
    interface Binders {
        @Binds
        fun bindAuthRepository(authRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    }
}