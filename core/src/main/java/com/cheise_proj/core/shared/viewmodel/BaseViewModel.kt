package com.cheise_proj.core.shared.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposable: CompositeDisposable = CompositeDisposable()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isError: MutableLiveData<Boolean> = MutableLiveData(false)
    val message: MutableLiveData<String?> = MutableLiveData()

    fun setLoading(status: Boolean) {
        isLoading.postValue(status)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}