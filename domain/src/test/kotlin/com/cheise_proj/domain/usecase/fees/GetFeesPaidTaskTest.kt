package com.cheise_proj.domain.usecase.fees

import com.cheise_proj.domain.repository.FeesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import utils.FakeFees

class GetFeesPaidTaskTest {

    private lateinit var getFeesPaidTask: GetFeesPaidTask

    @Mock
    lateinit var feesRepository: FeesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getFeesPaidTask =
            GetFeesPaidTask(feesRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun `should fetch student fees paid`() {
        val feesPaid = FakeFees.getFeesPaid()
        Mockito.`when`(feesRepository.getFeesPaid()).thenReturn(Observable.just(feesPaid))
        getFeesPaidTask.execute()
            .test()
            .assertValueCount(1)
            .assertComplete()
    }
}