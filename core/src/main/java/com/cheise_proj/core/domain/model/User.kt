package com.cheise_proj.core.domain.model

data class User(
    var id: Int,
    var username: String?,
    var type: String?,
    var iat: Long?,
    var exp: Long?
) {
    constructor() : this(0, null, null, null, null)
}