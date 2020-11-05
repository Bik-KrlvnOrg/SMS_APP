package com.cheise_proj.core.data.repository

import com.cheise_proj.core.data.local.dao.ProfileDao
import com.cheise_proj.core.data.local.mapper.mapEntityTo
import com.cheise_proj.core.data.remote.mapper.mapFrom
import com.cheise_proj.core.data.remote.service.ApiService
import com.cheise_proj.core.domain.model.Student
import com.cheise_proj.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val profileDao: ProfileDao
) : UserRepository<Student> {
    override fun getProfile(): Flow<Student> {
        return flow {
            val data = apiService.studentProfile()
            Timber.i("student_dto: $data")
            val profile = data.studentProfile
            val entity = profile.mapFrom()
            Timber.i("save_student: $entity")
            profileDao.saveStudent(entity.mapEntityTo())
            emit(profile.mapFrom())
        }

    }
}