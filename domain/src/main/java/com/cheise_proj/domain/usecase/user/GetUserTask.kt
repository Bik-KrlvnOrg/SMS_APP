package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.qualifiers.Background
import com.cheise_proj.domain.repository.AuthenticationRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Foreground
import com.cheise_proj.domain.usecase.NoParam
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class GetUserTask @Inject constructor(
    private val authRepository: AuthenticationRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<User, NoParam>(backgroundScheduler, foregroundScheduler) {
    override fun generateObservable(input: NoParam?): Observable<User> {
        return authRepository.getUser()
    }
}