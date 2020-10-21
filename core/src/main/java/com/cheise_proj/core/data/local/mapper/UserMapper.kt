package com.cheise_proj.core.data.local.mapper

import com.cheise_proj.core.data.Mapper
import com.cheise_proj.core.data.local.entity.UserEntity
import com.cheise_proj.core.domain.model.User

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