package com.cheise_proj.infrastructure.remote.mapper

import com.cheise_proj.domain.model.Token
import com.cheise_proj.infrastructure.Mapper
import com.cheise_proj.infrastructure.remote.model.dto.TokenDto

object TokenMapper : Mapper<TokenDto, Token> {
    override fun mapTo(t: TokenDto): Token {
        return Token(
            accessToken = t.accessToken,
            refreshToken = t.refreshToken,
            tokenType = t.tokenType
        )
    }

    override fun mapFrom(e: Token): TokenDto {
        return TokenDto(
            accessToken = e.accessToken,
            refreshToken = e.refreshToken,
            tokenType = e.tokenType
        )
    }
}

internal fun TokenDto.mapTo() = TokenMapper.mapTo(this)
internal fun Token.mapFrom() = TokenMapper.mapFrom(this)