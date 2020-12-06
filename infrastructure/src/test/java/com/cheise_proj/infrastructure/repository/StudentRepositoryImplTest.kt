package com.cheise_proj.infrastructure.repository

import com.cheise_proj.infrastructure.local.dao.ProfileDao
import com.cheise_proj.infrastructure.remote.service.ApiService
import com.cheise_proj.infrastructure.utils.FakeUser
import io.reactivex.Single
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StudentRepositoryImplTest {
    companion object {
        private const val REMOTE_ERROR = "an error occurred"
        private const val LOCAL_ERROR = "an error occurred"
    }

    private lateinit var studentRepositoryImpl: StudentRepositoryImpl

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var profileDao: ProfileDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        studentRepositoryImpl = StudentRepositoryImpl(apiService, profileDao)
    }

    @Test
    fun `should return current user profile`() {
        val studentDto = FakeUser.getStudentDto()
        val studentProfile = FakeUser.getStudentProfile()
        Mockito.`when`(apiService.studentProfile()).thenReturn(Observable.just(studentDto))
        Mockito.`when`(profileDao.getStudentProfile()).thenReturn(io.reactivex.Observable.just(studentProfile))
        studentRepositoryImpl.getProfile()
            .test()
            .assertValueCount(2)
            .assertComplete()
    }

    @Test
    fun `should return local profile if remote fails`() {
        val studentProfile = FakeUser.getStudentProfile()
        Mockito.`when`(apiService.studentProfile()).thenReturn(
            Observable.error(
                Throwable(
                    REMOTE_ERROR
                )
            )
        )
        Mockito.`when`(profileDao.getStudentProfile()).thenReturn(io.reactivex.Observable.just(studentProfile))
        studentRepositoryImpl.getProfile()
            .test()
            .assertValueCount(1)
    }

    @Test
    fun `should return remote profile if local fails`() {
        val studentDto = FakeUser.getStudentDto()
        Mockito.`when`(apiService.studentProfile()).thenReturn(
            Observable.just(studentDto)
        )
        Mockito.`when`(profileDao.getStudentProfile())
            .thenReturn(io.reactivex.Observable.error(Throwable(LOCAL_ERROR)))
        studentRepositoryImpl.getProfile()
            .test()
            .assertValueCount(1)
    }
}