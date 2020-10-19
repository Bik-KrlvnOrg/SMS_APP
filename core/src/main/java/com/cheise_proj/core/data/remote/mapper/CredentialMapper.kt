package com.cheise_proj.core.data.remote.mapper

import com.cheise_proj.core.data.Mapper
import com.cheise_proj.core.data.remote.model.CredentialDto
import com.cheise_proj.core.domain.model.Credential
import java.util.*

object CredentialMapper : Mapper<Credential, CredentialDto> {
    override fun mapTo(t: Credential): CredentialDto {
        return CredentialDto(
            username = t.username,
            password = t.password,
            type = t.type.toLowerCase(Locale.getDefault())
        )
    }

    override fun mapFrom(e: CredentialDto): Credential {
        return Credential(
            username = e.username,
            password = e.password,
            type = e.type.toLowerCase(Locale.getDefault())
        )
    }
}

internal fun Credential.mapTo() = CredentialMapper.mapTo(this)
internal fun CredentialDto.mapFrom() = CredentialMapper.mapFrom(this)