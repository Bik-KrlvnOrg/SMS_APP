package com.cheise_proj.infrastructure.remote.model

data class CredentialDto(
    val username: String,
    val password: String,
    val type: String
)