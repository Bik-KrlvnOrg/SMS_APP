package com.cheise_proj.domain.model

data class User(
    var id: Int,
    var username: String?,
    var type: String?,
    var iat: Long?,
    var exp: Long?,
    var accessToken: String?,
    var refreshToken: String?
) {
    constructor() : this(0, null, null, null, null, null, null)
}