package com.cheise_proj.core.shared.ui.auth.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.toLiveData
import com.cheise_proj.core.shared.data.model.Resource
import com.cheise_proj.core.shared.viewmodel.BaseViewModel
import com.cheise_proj.domain.model.User
import com.cheise_proj.domain.usecase.user.GetUserTask
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val getUserTask: GetUserTask) :
    BaseViewModel() {
    private val _userData: MutableLiveData<Resource<User>> = MutableLiveData()
    val userData: LiveData<Resource<User>> = _userData

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
       disposable.add(
           getUserTask.execute()
               .map {
                   Resource.success(it)
               }
               .startWith(Observable.just(Resource.loading()))
               .onErrorResumeNext { Observable.just(Resource.error(it.localizedMessage)) }
               .subscribe({
                   Timber.i("user: $it")
                          _userData.value = it
               },{Timber.e(it,it.localizedMessage)})
       )
    }


}