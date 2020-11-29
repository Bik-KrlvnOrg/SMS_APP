package com.cheise_proj.infrastructure.local.mapper

import com.cheise_proj.domain.model.User
import com.cheise_proj.infrastructure.Mapper
import com.cheise_proj.infrastructure.local.entity.UserEntity

object UserMapper : Mapper<User, UserEntity> {
    override fun mapTo(t: User): UserEntity {
        return UserEntity(
            id = t.id,
            username = t.username,
            type = t.type ?: "",
            exp = 0,
            iat = 0
        )
    }

    override fun mapFrom(e: UserEntity): User {
        return User().apply {
            id = e.id
            username = e.username
            type = e.type
            exp = e.exp
            iat = e.iat
        }
    }
}

internal fun User.mapTo() = UserMapper.mapTo(this)
internal fun UserEntity.mapFrom() = UserMapper.mapFrom(this)