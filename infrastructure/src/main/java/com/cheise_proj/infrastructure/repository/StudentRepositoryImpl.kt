package com.cheise_proj.infrastructure.repository

import com.cheise_proj.domain.model.Student
import com.cheise_proj.domain.repository.UserRepository
import com.cheise_proj.infrastructure.local.dao.ProfileDao
import com.cheise_proj.infrastructure.local.mapper.mapEntityTo
import com.cheise_proj.infrastructure.local.mapper.mapFrom
import com.cheise_proj.infrastructure.remote.mapper.mapFrom
import com.cheise_proj.infrastructure.remote.service.ApiService
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val profileDao: ProfileDao
) : UserRepository<Student> {
    companion object {
        private const val PROFILE_ERROR = "profile not found"
    }

    override fun getProfile(): Observable<Student?> {
        val local = RxJavaBridge.toV3Observable(
            profileDao.getStudentProfile()
                .map {
                    it.mapFrom()
                }
        )
        val remote = apiService.studentProfile()
            .map {
                Timber.i("student_dto: $it")
                it.studentProfile.mapFrom()
            }
            .map {
                Timber.i("save_student: $it")
                profileDao.saveStudent(it.mapEntityTo())
                it
            }.onErrorResumeNext { Observable.error(Throwable(it.localizedMessage)) }

        return Observable.mergeDelayError(local, remote)
    }

}