package com.cheise_proj.infrastructure.repository

import com.cheise_proj.infrastructure.local.dao.FeesDao
import com.cheise_proj.infrastructure.remote.service.ApiService
import com.cheise_proj.infrastructure.utils.FakeFees
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FeesRepositoryImplTest {

    private lateinit var feesRepositoryImpl: FeesRepositoryImpl

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var feesDao: FeesDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        feesRepositoryImpl = FeesRepositoryImpl(apiService, feesDao)
    }

    @Test
    fun `should fetch fees paid`() {
        val feesPaidDto = FakeFees.getFeesPaidDto()
        val feesPaidEntity = FakeFees.getFeesPaidEntity()
        Mockito.`when`(apiService.getFeesPaid()).thenReturn(Observable.just(feesPaidDto))
        Mockito.`when`(feesDao.getFeesPaid())
            .thenReturn(io.reactivex.Observable.just(feesPaidEntity))
        feesRepositoryImpl.getFeesPaid().test()
            .assertValueCount(2)
            .assertComplete()

    }

    @Test
    fun `should fetch fees paid from remote if local fails`() {
        val feesPaidDto = FakeFees.getFeesPaidDto()
        Mockito.`when`(apiService.getFeesPaid()).thenReturn(Observable.just(feesPaidDto))
        Mockito.`when`(feesDao.getFeesPaid())
            .thenReturn(io.reactivex.Observable.error(Throwable("something went wrong")))
        feesRepositoryImpl.getFeesPaid().test()
            .assertValueCount(1)
            .assertNotComplete()

    }

    @Test
    fun `should fetch fees paid from local if remote fails`() {
        val feesPaidEntity = FakeFees.getFeesPaidEntity()

        Mockito.`when`(apiService.getFeesPaid())
            .thenReturn(Observable.error(Throwable("something went wrong")))
        Mockito.`when`(feesDao.getFeesPaid())
            .thenReturn(io.reactivex.Observable.just(feesPaidEntity))
        feesRepositoryImpl.getFeesPaid().test()
            .assertValueCount(1)
            .assertNotComplete()

    }
}