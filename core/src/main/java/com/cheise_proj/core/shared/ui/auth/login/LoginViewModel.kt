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
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository,
    private val networkState: NetworkState
) : BaseViewModel() {
    private val _userData: MutableLiveData<User> = MutableLiveData()

    val username: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    var selectedItem: MutableLiveData<String> = MutableLiveData("")
    val canExecute: LiveData<Boolean> = isError
    val forgetPassword: MutableLiveData<Boolean> = MutableLiveData(false)
    val userData: LiveData<User> = _userData

    @ExperimentalCoroutinesApi
    fun onLogin() {
        val user = LoginUser(username.value, password.value, selectedItem.value)
        message.value = null
        if (!networkState.isConnected()) {
            message.value = "No network"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {

                authRepository.authenticateUser(
                    username = user.username ?: "",
                    password = user.password ?: "",
                    type = user.type ?: ""
                ).onStart {
                    setLoading(true)
                }.onCompletion {
                    setLoading(false)
                }.collect { user ->
                    _userData.postValue(user)
                    message.postValue("welcome: ${user.username}")
                }
            } catch (e: Exception) {
                setLoading(false)
                message.postValue(e.localizedMessage)
                Timber.e(e, e.localizedMessage)
            }
        }
    }

    fun onForgetPassword() {
        forgetPassword.value = true
    }

    private fun validateLength(value: String): Boolean = value.length > 3
    private fun validateText(value: String): Boolean = value.isNotEmpty()
    private fun validateRole(value: String): Boolean = value.isNotEmpty() && value != "Role"

    private val isUsernameValid: LiveData<Boolean> =
        Transformations.map(username) { validateText(it) }

    private val isPasswordValid: LiveData<Boolean> =
        Transformations.map(password) { validateLength(it) }

    private val isUserTypeValid: LiveData<Boolean> =
        Transformations.map(selectedItem) { validateRole(it) }

    private fun isValidData(): Boolean {
        return isUsernameValid.value ?: false &&
                isPasswordValid.value ?: false &&
                isUserTypeValid.value ?: false
    }


    val usernameError: LiveData<String> = Transformations.map(isUsernameValid) {
        isError.value = isValidData()
        when (it) {
            true -> null
            false -> "enter username"
        }
    }
    val passwordError: LiveData<String> = Transformations.map(isPasswordValid) {
        isError.value = isValidData()
        when (it) {
            true -> null
            false -> "invalid password"
        }
    }
    val userTypeError: LiveData<String> = Transformations.map(isUserTypeValid) {
        isError.value = isValidData()
        when (it) {
            true -> null
            false -> "select a role"
        }
    }

    fun onUserTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val usr = s.trim().toString()
        username.value = usr
    }

    fun onPasswordTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val pass = s.trim().toString()
        password.value = pass
    }
}