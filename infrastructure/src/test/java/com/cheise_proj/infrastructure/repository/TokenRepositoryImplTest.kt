package com.cheise_proj.infrastructure.repository

import com.cheise_proj.infrastructure.local.dao.UserDao
import com.cheise_proj.infrastructure.remote.service.ApiService
import com.cheise_proj.infrastructure.utils.FakeAuth
import dagger.Lazy
import io.reactivex.Single
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TokenRepositoryImplTest {
    companion object {
        private const val USERNAME = "any username"
        private const val ACCESS_TOKEN = "any access token"
        private const val REFRESH_TOKEN = "any refresh token"
        private const val ACCESS_TOKEN_KEY = "oldAccessToken"
        private const val REFRESH_TOKEN_KEY = "refreshToken"

    }

    private lateinit var tokenRepositoryImpl: TokenRepositoryImpl

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var userDao: UserDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tokenRepositoryImpl = TokenRepositoryImpl({ apiService }, userDao)
    }

    @Test
    fun `should get current user`() {
        val user = FakeAuth.getUserLocal()
        Mockito.`when`(userDao.getUser()).thenReturn(Single.just(user))
        tokenRepositoryImpl.getUser()
            .test()
            .assertValueCount(1)
            .assertComplete()
    }

    @Test
    fun `should fetch refresh token`() {
        val body = hashMapOf(ACCESS_TOKEN_KEY to ACCESS_TOKEN, REFRESH_TOKEN_KEY to REFRESH_TOKEN)
        val tokenDto = FakeAuth.getTokenDto()
        Mockito.`when`(apiService.refreshToken(body)).thenReturn(Observable.just(tokenDto))
        tokenRepositoryImpl.refreshToken(USERNAME, ACCESS_TOKEN, REFRESH_TOKEN)
            .test()
            .assertValueCount(1)
            .assertComplete()
    }
}