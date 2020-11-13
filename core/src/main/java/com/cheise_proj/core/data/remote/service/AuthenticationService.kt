package com.cheise_proj.core.data.remote.service

import com.cheise_proj.core.data.remote.model.CredentialDto
import com.cheise_proj.core.data.remote.model.dto.LoginDto
import com.cheise_proj.core.data.remote.model.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("users/login")
    suspend fun login(@Body credentialDtoDto: CredentialDto): LoginDto

    @POST("users/refresh-token")
    suspend fun refreshToken(@Body body: HashMap<String, String>):TokenDto
}