package com.cheise_proj.domain.rx

import com.cheise_proj.domain.usecase.BaseUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler

abstract class ObservableUseCase<T, in Input>(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler
) :
    BaseUseCase(backgroundScheduler, foregroundScheduler) {
    protected abstract fun generateObservable(input: Input?): Observable<T>

    fun execute(input: Input? = null): Observable<T> = generateObservable(input)
        .subscribeOn(backgroundScheduler)
        .observeOn(foregroundScheduler)
}