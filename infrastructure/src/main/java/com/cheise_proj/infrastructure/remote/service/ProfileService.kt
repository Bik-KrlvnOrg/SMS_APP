package com.cheise_proj.infrastructure.remote.service

import com.cheise_proj.infrastructure.remote.model.dto.StudentDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ProfileService {
    @GET("student/profile")
     fun studentProfile(): Observable<StudentDto>
}