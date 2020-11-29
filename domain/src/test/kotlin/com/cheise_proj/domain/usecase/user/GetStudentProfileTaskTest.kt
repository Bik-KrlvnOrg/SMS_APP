package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.model.Student
import com.cheise_proj.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import utils.FakeUser

class GetStudentProfileTaskTest {

    private lateinit var getStudentProfileTask: GetStudentProfileTask

    @Mock
    lateinit var userRepository: UserRepository<Student>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getStudentProfileTask =
            GetStudentProfileTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `show return student profile data`() {
        val student = FakeUser.getStudentProfile()
        Mockito.`when`(userRepository.getProfile()).thenReturn(Observable.just(student))
        getStudentProfileTask.execute(null)
            .test()
            .assertValueCount(1)
            .assertComplete()
            .assertNoErrors()
    }
}