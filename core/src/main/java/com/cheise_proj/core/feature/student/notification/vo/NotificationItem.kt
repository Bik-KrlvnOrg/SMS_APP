package com.cheise_proj.core.feature.student.notification.vo

import java.time.LocalDateTime

data class NotificationItem(
    val id: Int,
    val name: String,
    val body: String,
    val document: String,
    val dateTime: LocalDateTime
) {
    companion object {
        fun getNotificationData(): List<NotificationItem> {
            return arrayListOf(
                NotificationItem(
                    id = 1,
                    name = "Dorothy Richards",
                    body = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor",
                    document = "homework.pdf",
                    dateTime = LocalDateTime.now()
                ),
                NotificationItem(
                    id = 2,
                    name = "Ernest Williamson",
                    body = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor",
                    document = "chapter2.doc",
                    dateTime = LocalDateTime.now()
                ),
                NotificationItem(
                    id = 3,
                    name = "Vincent Johnson",
                    body = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor",
                    document = "chapter18.doc",
                    dateTime = LocalDateTime.now()
                )
            )
        }
    }
}