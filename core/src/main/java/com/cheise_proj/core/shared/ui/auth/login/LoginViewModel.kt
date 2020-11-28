package com.cheise_proj.core.shared.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cheise_proj.core.domain.model.User
import com.cheise_proj.core.domain.repository.AuthenticationRepository
import com.cheise_proj.core.shared.viewmodel.BaseViewModel
import com.cheise_proj.core.utils.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository,
    private val networkState: NetworkState
) : BaseViewModel() {

    private val passwordLength = 3
    private val _userData: MutableLiveData<User> = MutableLiveData()
    val onUsernameChange: MutableLiveData<String> = MutableLiveData()
    val onPasswordChange: MutableLiveData<String> = MutableLiveData()
    val onSelectionChange: MutableLiveData<String> = MutableLiveData()
    val usernameError: MutableLiveData<String> = MutableLiveData()
    val passwordError: MutableLiveData<String> = MutableLiveData()
    private val _canExecute: MutableLiveData<Boolean> = MutableLiveData(false)
    val canExecute: LiveData<Boolean> = _canExecute
    val userData: LiveData<User?> = _userData
    private val selection: LiveData<String> = onSelectionChange

    init {
        onUsernameChange.value = ""
        onPasswordChange.value = ""
    }


    val username: LiveData<String> = Transformations.map(onUsernameChange) {
        _canExecute.value = isValidateForm()
        if (!validateText(it)) {
            usernameError.value = "invalid username"
            return@map null
        }
        it
    }

    val password: LiveData<String> = Transformations.map(onPasswordChange) {
        _canExecute.value = isValidateForm()
        if (!validateLength(it)) {
            passwordError.value = "invalid password"
            return@map null
        }
        it
    }

    @ExperimentalCoroutinesApi
    fun onLogin() {
        val form = LoginUser(username.value, password.value, selection.value)
        message.value = null
        if (!networkState.isConnected()) {
            message.value = "No network"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {

                authRepository.authenticateUser(
                    username = form.username ?: "",
                    password = form.password ?: "",
                    type = form.type ?: ""
                ).onStart {
                    setLoading(true)
                    _canExecute.postValue(false)
                }.onCompletion {
                    setLoading(false)
                    _canExecute.postValue(true)

                }.collect {
                    it.type = it.type?.toUpperCase(Locale.getDefault())
                    Timber.i("user: $it")
                    _userData.postValue(it)
                }
            } catch (e: Exception) {
                setLoading(false)
                _canExecute.postValue(true)
                message.postValue(e.localizedMessage)
                Timber.e(e, e.localizedMessage)
            }
        }
    }

    private fun validateText(value: String): Boolean = value.isNotEmpty()
    private fun validateLength(value: String): Boolean = value.length > passwordLength

    private fun isValidateForm(): Boolean {
        return username.value?.isNotEmpty() ?: false &&
                password.value?.isNotEmpty() ?: false
    }


}