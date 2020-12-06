package com.cheise_proj.core.feature.student.ui.fees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cheise_proj.core.shared.data.model.Resource
import com.cheise_proj.core.shared.viewmodel.BaseViewModel
import com.cheise_proj.domain.model.FeesPaid
import com.cheise_proj.domain.usecase.fees.GetFeesPaidTask
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject

class FeesPaidViewModel @Inject constructor(
    private val getFeesPaidTask: GetFeesPaidTask
) : BaseViewModel() {
    private val _feesPaid = MutableLiveData<Resource<List<FeesPaid>>>()
    val feesPaid: LiveData<Resource<List<FeesPaid>>> = _feesPaid

    init {
        loadFeesPaid()
    }

    private fun loadFeesPaid() {
        disposable.add(
            getFeesPaidTask.execute()
                .map {
                    Resource.success(it)
                }.startWith(Observable.just(Resource.loading()))
                .onErrorResumeNext { Observable.just(Resource.error(it.localizedMessage)) }
                .subscribe({
                    _feesPaid.value = it
                }, { Timber.e(it, it.localizedMessage) })
        )
    }

}