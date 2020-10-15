package com.cheise_proj.core.domain.model

import com.cheise_proj.core.common.AuthType

data class User(
    val id: Int,
    val username: String,
    val type: AuthType,
    val school: Int?,
    val departmentId: Int?
)