package com.cheise_proj.sms_app.di.module.infrastructure.repository


import com.cheise_proj.domain.model.Student
import com.cheise_proj.domain.repository.AuthenticationRepository
import com.cheise_proj.domain.repository.FeesRepository
import com.cheise_proj.domain.repository.TokenRepository
import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.infrastructure.repository.AuthenticationRepositoryImpl
import com.cheise_proj.infrastructure.repository.FeesRepositoryImpl
import com.cheise_proj.infrastructure.repository.StudentRepositoryImpl
import com.cheise_proj.infrastructure.repository.TokenRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.Binders::class])
class RepositoryModule {
    @Module
    interface Binders {
        @Binds
        fun bindFeesRepository(feesRepositoryImpl: FeesRepositoryImpl): FeesRepository

        @Binds
        fun bindAuthRepository(authRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

        @Binds
        fun bindStudentRepository(studentRepositoryImpl: StudentRepositoryImpl): UserRepository<Student>

        @Binds
        fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    }
}