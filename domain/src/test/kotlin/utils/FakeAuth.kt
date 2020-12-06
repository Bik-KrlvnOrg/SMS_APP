package utils

import com.cheise_proj.domain.model.User

object FakeAuth {
    fun getUser(): User {
        return User(
            id = 1,
            username = "any username",
            accessToken = null,
            exp = null,
            iat = null,
            refreshToken = null,
            type = "any type"

        )
    }


}