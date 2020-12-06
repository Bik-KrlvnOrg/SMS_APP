package com.cheise_proj.infrastructure.remote.service

import com.cheise_proj.infrastructure.remote.model.dto.FeesPaidDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface FeesService {
    @GET("fees/student")
    fun getFeesPaid(): Observable<FeesPaidDto>
}