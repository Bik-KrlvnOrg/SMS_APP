package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.model.Student
import com.cheise_proj.domain.qualifiers.Background
import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Foreground
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class GetStudentProfileTask @Inject constructor(
    private val userRepository: UserRepository<Student>,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<Student?, GetStudentProfileTask.Params>(
    backgroundScheduler,
    foregroundScheduler
) {

    class Params

    override fun generateObservable(input: Params?): Observable<Student?> {
        return userRepository.getProfile()
    }





}