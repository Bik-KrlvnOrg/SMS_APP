package com.cheise_proj.core.domain.model

data class Notice(
    val date: String,
    val id: Int,
    val message: String,
    val subject: String,
    val title: String
)
