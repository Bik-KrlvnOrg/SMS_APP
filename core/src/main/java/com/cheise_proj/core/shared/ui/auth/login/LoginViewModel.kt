package com.cheise_proj.core.shared.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.cheise_proj.core.shared.data.model.Resource
import com.cheise_proj.core.shared.viewmodel.BaseViewModel
import com.cheise_proj.core.utils.NetworkState
import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.usecase.auth.AuthenticationTask
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authenticationTask: AuthenticationTask,
    private val networkState: NetworkState
) : BaseViewModel() {

    private val passwordLength = 3
    private var _userData: MutableLiveData<Resource<User>> = MutableLiveData()
    val onUsernameChange: MutableLiveData<String> = MutableLiveData()
    val onPasswordChange: MutableLiveData<String> = MutableLiveData()
    val onSelectionChange: MutableLiveData<String> = MutableLiveData()
    val usernameError: MutableLiveData<String> = MutableLiveData()
    val passwordError: MutableLiveData<String> = MutableLiveData()
    private val _canExecute: MutableLiveData<Boolean> = MutableLiveData(false)
    val canExecute: LiveData<Boolean> = _canExecute
    val userData: LiveData<Resource<User>> = _userData
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
        disposable.add(
            authenticationTask.execute(
                AuthenticationTask.Params(
                    form.username!!,
                    form.password!!,
                    form.type!!
                )
            )
                .map {
                    it.type = it.type?.toUpperCase(Locale.getDefault())
                    it
                }
                .map {
                    Resource.success(it)
                }
                .startWith(Observable.just(Resource.loading()))
                .onErrorResumeNext { Observable.just(Resource.error(it.localizedMessage)) }
                .subscribe({
                    _userData.value = it
                }, { Timber.e(it, it.localizedMessage) })
        )
    }

    private fun validateText(value: String): Boolean = value.isNotEmpty()
    private fun validateLength(value: String): Boolean = value.length > passwordLength

    private fun isValidateForm(): Boolean {
        return username.value?.isNotEmpty() ?: false &&
                password.value?.isNotEmpty() ?: false
    }


}