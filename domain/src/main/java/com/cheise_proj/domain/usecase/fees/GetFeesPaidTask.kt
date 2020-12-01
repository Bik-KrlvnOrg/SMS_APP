package com.cheise_proj.domain.usecase.fees

import com.cheise_proj.domain.model.FeesPaid
import com.cheise_proj.domain.qualifiers.Background
import com.cheise_proj.domain.repository.FeesRepository
import com.cheise_proj.domain.rx.ObservableUseCase
import com.cheise_proj.domain.rx.qualifier.Foreground
import com.cheise_proj.domain.usecase.NoParam
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class GetFeesPaidTask @Inject constructor(
    private val feesRepository: FeesRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) :
    ObservableUseCase<List<FeesPaid>, NoParam>(backgroundScheduler, foregroundScheduler) {
    override fun generateObservable(input: NoParam?): Observable<List<FeesPaid>> {
        return feesRepository.getFeesPaid()
    }
}