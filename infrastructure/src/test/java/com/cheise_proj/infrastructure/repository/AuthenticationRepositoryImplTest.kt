package com.cheise_proj.infrastructure.repository

import com.cheise_proj.infrastructure.local.dao.UserDao
import com.cheise_proj.infrastructure.remote.model.CredentialDto
import com.cheise_proj.infrastructure.remote.service.ApiService
import com.cheise_proj.infrastructure.utils.FakeAuth
import com.cheise_proj.infrastructure.utils.JwtService
import io.reactivex.Single
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AuthenticationRepositoryImplTest {
    companion object {
        private const val USERNAME = "any username"
        private const val PASSWORD = "any password"
        private const val TYPE = "any type"
        private const val USER_ERROR = "user not found"
    }

    private lateinit var authRepositoryImpl: AuthenticationRepositoryImpl

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var userDao: UserDao

    @Mock
    lateinit var jwtService: JwtService


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        authRepositoryImpl = AuthenticationRepositoryImpl(apiService, userDao, jwtService)
    }

    @Test
    fun `authenticate user success`() {
        val user = FakeAuth.getUser()
        val loginDto = FakeAuth.getLoginDto()
        Mockito.`when`(apiService.login(CredentialDto(USERNAME, PASSWORD, TYPE))).thenReturn(
            Observable.just(loginDto)
        )
        Mockito.`when`(jwtService.decodeBody(anyString())).thenReturn(user)
        authRepositoryImpl.authenticateUser(USERNAME, PASSWORD, TYPE)
            .test()
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it == user }

    }

    @Test
    fun `should throw an error with user is not found`() {
        Mockito.`when`(userDao.getUser()).thenReturn(Single.error(Throwable(USER_ERROR)))
        authRepositoryImpl.getUser()
            .test()
            .assertNotComplete()
            .assertError { it.localizedMessage == USER_ERROR }
    }

    @Test
    fun `should return authenticated user data`() {
        val user = FakeAuth.getUserLocal()
        Mockito.`when`(userDao.getUser()).thenReturn(Single.just(user))
        authRepositoryImpl.getUser()
            .test()
            .assertValueCount(1)
            .assertComplete()
    }
}