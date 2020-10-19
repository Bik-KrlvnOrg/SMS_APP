package com.cheise_proj.core.data.remote.model.dto


import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("tokenType")
    val tokenType: String
)