package com.cheise_proj.core.data.remote.service

import com.cheise_proj.core.data.remote.model.dto.StudentDto
import retrofit2.http.GET

interface ProfileService {
    @GET("student/profile")
    suspend fun studentProfile():StudentDto
}