package com.cheise_proj.infrastructure.repository

import com.cheise_proj.domain.model.FeesPaid
import com.cheise_proj.domain.repository.FeesRepository
import com.cheise_proj.infrastructure.local.dao.FeesDao
import com.cheise_proj.infrastructure.local.mapper.mapFromList
import com.cheise_proj.infrastructure.local.mapper.mapToList
import com.cheise_proj.infrastructure.remote.mapper.mapDtoListTo
import com.cheise_proj.infrastructure.remote.service.ApiService
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject

class FeesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val feesDao: FeesDao
) : FeesRepository {
    override fun getFeesPaid(): Observable<List<FeesPaid>> {
        val local = RxJavaBridge.toV3Observable(feesDao.getFeesPaid()
            .map {
                Timber.i("feesPaidLocal: $it")
                it.mapToList()
            })
        val remote = apiService.getFeesPaid()
            .map {
                Timber.i("feesPaidDto: $it")
                val mapDtoListTo = it.feesPaid.mapDtoListTo()
                Timber.i("saveFeesPaid: $it")
                feesDao.saveFeesPaid(mapDtoListTo.mapFromList())
                it.feesPaid.mapDtoListTo()
            }
        return Observable.mergeDelayError(local, remote)
    }
}