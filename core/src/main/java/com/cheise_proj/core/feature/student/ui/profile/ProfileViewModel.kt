package com.cheise_proj.core.feature.student.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.core.domain.model.Student
import com.cheise_proj.core.domain.repository.UserRepository
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
    private val _studentProfile: MutableLiveData<HashMap<String,String>> = MutableLiveData()
    val studentProfile: LiveData<HashMap<String,String>> = _studentProfile

    init {
        loadStudentProfile()
    }

    private fun loadStudentProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                userRepository.getProfile()
                    .onStart { isLoading.postValue(true) }
                    .map {
                        val hashMap = HashMap<String, String>()
                        hashMap["Username"] = it.username
                        hashMap["Gender"] = it.gender
                        hashMap["Name"] = "${it.firstName} ${it.middleName} ${it.lastName}"
                        hashMap["Email"] = it.email
                        hashMap["Contact"] = it.contact
                        hashMap["Admission Status"] = it.admissionStatus
                        hashMap["Dob"] = it.dob
                        hashMap
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