package com.cheise_proj.domain.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
){
    constructor():this("","","")
}