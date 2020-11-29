package com.cheise_proj.core.feature.student.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cheise_proj.core.shared.viewmodel.BaseViewModel
import com.cheise_proj.domain.usecase.user.GetStudentProfileTask
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ProfileViewModel @Inject constructor(
    private val getStudentProfileTask: GetStudentProfileTask
) :
    BaseViewModel() {
    private val _studentProfile: MutableLiveData<HashMap<String, String?>> = MutableLiveData()
    val studentProfile: LiveData<HashMap<String, String?>> = _studentProfile

    init {
        loadStudentProfile()
    }

    private fun loadStudentProfile() {
        isLoading.postValue(true)
        disposable.add(
            getStudentProfileTask.execute()
                .map {
                    val hashMap = HashMap<String, String?>()
                    hashMap["Username"] = it?.username
                    hashMap["Gender"] = it?.gender
                    hashMap["Name"] = "${it?.firstName} ${it?.middleName} ${it?.lastName}"
                    hashMap["Email"] = it?.email
                    hashMap["Contact"] = it?.contact
                    hashMap["Admission Status"] = it?.admissionStatus
                    hashMap["Dob"] = it?.dob
                    hashMap
                }
                .subscribe({
                    _studentProfile.value = it
                    isLoading.value = false
                    Timber.i("student: $it")
                }, {
                    Timber.e(it, it.localizedMessage)
                    message.value = it.localizedMessage
                })
        )
    }

}