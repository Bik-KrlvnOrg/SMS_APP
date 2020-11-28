package com.cheise_proj.core.shared.ui.auth.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.core.domain.model.User
import com.cheise_proj.core.domain.repository.AuthenticationRepository
import com.cheise_proj.core.shared.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val authRepository: AuthenticationRepository) :
    BaseViewModel() {
    private val _userData: MutableLiveData<User> = MutableLiveData()
    val userData: LiveData<User?> = _userData

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.getUser().collect {
                Timber.i("user: $it")
                _userData.postValue(it)
            }

        }
    }


}