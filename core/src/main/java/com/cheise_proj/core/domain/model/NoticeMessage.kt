package com.cheise_proj.core.domain.model

data class NoticeMessage(
    val createdOn: String,
    val fromId: Int,
    val fromStatus: String,
    val fromType: String,
    val id: Int,
    val message: String,
    val readStatus: String,
    val repliedMessageId: String,
    val replyStatus: String,
    val status: String,
    val subject: String,
    val toId: Int,
    val toStatus: String,
    val toType: String
)
