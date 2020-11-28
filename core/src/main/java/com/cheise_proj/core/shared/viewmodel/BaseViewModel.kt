package com.cheise_proj.core.shared.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isError: MutableLiveData<Boolean> = MutableLiveData(false)
    val message: MutableLiveData<String?> = MutableLiveData()

    fun setLoading(status: Boolean) {
        isLoading.postValue(status)
    }
}