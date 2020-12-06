package com.cheise_proj.domain.repository

import com.cheise_proj.domain.model.FeesPaid
import io.reactivex.rxjava3.core.Observable

interface FeesRepository {
    fun getFeesPaid(): Observable<List<FeesPaid>>
}