package com.cheise_proj.core.feature.student.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.core.domain.model.Student
import com.cheise_proj.core.domain.repository.UserRepository
import com.cheise_proj.core.extension.serializeToMap
import com.cheise_proj.core.shared.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository<Student>
) :
    BaseViewModel() {
    private val _studentProfile: MutableLiveData<List<String>> = MutableLiveData()
    val studentProfile: LiveData<List<String>> = _studentProfile

    init {
        loadStudentProfile()
    }

    private fun loadStudentProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                userRepository.getProfile()
                    .onStart { isLoading.postValue(true) }
                    .map { student ->
                        val serialize = student.serializeToMap()
                        val list = arrayListOf<String>()
                        serialize.forEach {
                            list.add(it.value.toString())
                        }
                        list
                    }
                    .collect {
                        isLoading.postValue(false)
                        Timber.i("student: $it")

                        _studentProfile.postValue(it)
                    }
            } catch (e: Exception) {
                Timber.e(e, e.localizedMessage)
                message.postValue(e.localizedMessage)
                isLoading.postValue(false)
            }
        }
    }

}