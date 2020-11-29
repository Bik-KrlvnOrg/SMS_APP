package com.cheise_proj.domain.usecase.user

import com.cheise_proj.domain.repository.AuthenticationRepository
import com.cheise_proj.domain.usecase.auth.AuthenticationTask
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import utils.FakeAuth

class AuthenticationTaskTest {
    companion object {
        private const val USER_NAME = "any_username"
        private const val USER_PASSWORD = "any_username"
        private const val USER_TYPE = "Student"
    }

    private lateinit var authenticationTask: AuthenticationTask

    @Mock
    lateinit var authRepository: AuthenticationRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        authenticationTask =
            AuthenticationTask(authRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw an error if username is not provided`() {
        val username = ""
        authenticationTask.execute(AuthenticationTask.Params(username, USER_PASSWORD, USER_TYPE))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw an error if password is not provided`() {
        val password = ""
        authenticationTask.execute(AuthenticationTask.Params(USER_NAME, password, USER_TYPE))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw an error if type is not provided`() {
        val type = ""
        authenticationTask.execute(AuthenticationTask.Params(USER_NAME, USER_PASSWORD, type))
    }

    @Test
    fun `should return user data if valid credentials`() {
        val user = FakeAuth.getUser()

        Mockito.`when`(authRepository.authenticateUser(anyString(), anyString(), anyString()))
            .thenReturn(Observable.just(user))

        val testObserver = authenticationTask.execute(
            AuthenticationTask.Params(
                USER_NAME,
                USER_PASSWORD,
                USER_TYPE
            )
        ).test()

        testObserver.assertValue { it == user }
    }

}