package com.cheise_proj.infrastructure.remote.service


import com.cheise_proj.infrastructure.remote.model.CredentialDto
import com.cheise_proj.infrastructure.remote.model.dto.LoginDto
import com.cheise_proj.infrastructure.remote.model.dto.TokenDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("users/login")
    fun login(@Body credentialDtoDto: CredentialDto): Observable<LoginDto>

    @POST("users/refresh-token")
    fun refreshToken(@Body body: HashMap<String, String>): Observable<TokenDto>
}