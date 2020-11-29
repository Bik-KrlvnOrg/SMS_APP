package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.repository.AuthenticationRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import utils.FakeAuth

class GetUserTaskTest {

    private lateinit var getUserTask: GetUserTask

    @Mock
    lateinit var authRepository: AuthenticationRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserTask = GetUserTask(authRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `should get current user`() {
        val user = FakeAuth.getUser()
        Mockito.`when`(authRepository.getUser()).thenReturn(Observable.just(user))
        getUserTask.execute()
            .test()
            .assertValueCount(1)
            .assertComplete()
    }
}