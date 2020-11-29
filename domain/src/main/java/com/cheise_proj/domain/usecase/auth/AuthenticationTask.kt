package com.cheise_proj.domain.usecase.auth


import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.qualifiers.Background
import com.cheise_proj.domain.repository.AuthenticationRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Foreground

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class AuthenticationTask @Inject constructor(
    private val authRepository: AuthenticationRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<User, AuthenticationTask.Params>(
        backgroundScheduler,
        foregroundScheduler
    ) {

    override fun generateObservable(input: Params?): Observable<User> {
        if (input == null) throw IllegalArgumentException("input params required")
        if (input.username.isEmpty()) throw IllegalArgumentException("provide a username")
        if (input.password.isEmpty()) throw IllegalArgumentException("provide a password")
        if (input.type.isEmpty()) throw IllegalArgumentException("provide a user type")

        return with(input) {
            authRepository.authenticateUser(username, password, type)
        }
    }

    data class Params(
        val username: String,
        val password: String,
        val type: String
    )
}
