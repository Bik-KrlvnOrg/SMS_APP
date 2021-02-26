package com.cheise_proj.core.feature.student.notification.vo

import java.time.LocalDateTime


data class AnnouncementItem(
    val id: Int,
    val header: String,
    val body: String,
    val caption: String,
    val venue: String,
    val dateTime: LocalDateTime
) {
    companion object {
        fun getAnnouncementData(): List<AnnouncementItem> {
            return arrayListOf(
                AnnouncementItem(
                    id = 1,
                    header = "National Science Day",
                    body = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
                            "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat," +
                            " sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. ",
                    caption = "Headmaster",
                    venue = "Auditorium Exibition of London School",
                    dateTime = LocalDateTime.now()
                ),
                AnnouncementItem(
                    id = 2,
                    header = "Book Fair",
                    body = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
                            "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat," +
                            " sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. ",
                    caption = "Headmaster",
                    venue = "School library",
                    dateTime = LocalDateTime.now()
                ),
                AnnouncementItem(
                    id = 3,
                    header = "Fun Fair",
                    body = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
                            "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat," +
                            " sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. ",
                    caption = "Headmaster",
                    venue = "School Park",
                    dateTime = LocalDateTime.now()
                )
            )

        }

        fun getSelectedData(position: Int): AnnouncementItem {
            return getAnnouncementData()[position]
        }
    }
}