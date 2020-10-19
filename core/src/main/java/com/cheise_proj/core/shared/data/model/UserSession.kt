package com.cheise_proj.core.shared.data.model

data class UserSession(
    val userId: Int,
    val displayName: String?,
    val userType: String?,
    val schoolId: String?
)