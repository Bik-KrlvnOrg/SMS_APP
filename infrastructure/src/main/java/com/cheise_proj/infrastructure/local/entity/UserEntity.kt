package com.cheise_proj.infrastructure.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var username: String?,
    var type: String,
    var iat: Long?,
    var exp: Long?,
    var accessToken: String?,
    var refreshToken: String?
)