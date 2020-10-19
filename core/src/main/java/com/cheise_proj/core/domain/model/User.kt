package com.cheise_proj.core.domain.model

import com.cheise_proj.core.common.AuthType

data class User(
    var id: Int,
    var username: String?,
    var type: AuthType?,
    var iat: Long?,
    var exp: Long?
) {
    constructor() : this(0, null, null, null, null)
}