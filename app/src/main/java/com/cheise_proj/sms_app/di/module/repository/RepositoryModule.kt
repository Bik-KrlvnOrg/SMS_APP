package com.cheise_proj.sms_app.di.module.repository

import com.cheise_proj.core.data.repository.AuthenticationRepositoryImpl
import com.cheise_proj.core.data.repository.StudentRepositoryImpl
import com.cheise_proj.core.data.repository.TokenRepositoryImpl
import com.cheise_proj.core.domain.model.Student
import com.cheise_proj.core.domain.repository.AuthenticationRepository
import com.cheise_proj.core.domain.repository.TokenRepository
import com.cheise_proj.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.Binders::class])
class RepositoryModule {
    @Module
    interface Binders {
        @Binds
        fun bindAuthRepository(authRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

        @Binds
        fun bindStudentRepository(studentRepositoryImpl: StudentRepositoryImpl): UserRepository<Student>

        @Binds
        fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    }
}