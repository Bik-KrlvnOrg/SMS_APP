package com.cheise_proj.core.feature.student.academics.task.vo

import com.cheise_proj.core.R
import java.util.*

data class TaskItem(
    val id: Int,
    val title: String,
    val subTitle: String,
    val date: String,
    val status: String,
    val image: Int? = null,
    val mimeType: String
) {
    companion object {
        fun getTaskData(): List<TaskItem> {
            return arrayListOf(
                TaskItem(
                    id = 1,
                    title = "Homework 04",
                    subTitle = "Biology",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.docx,
                    mimeType = "docx"
                ),

                TaskItem(
                    id = 2,
                    title = "Homework 03",
                    subTitle = "History",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.pdf,
                    mimeType = "pdf"

                ),

                TaskItem(
                    id = 3,
                    title = "Homework 03",
                    subTitle = "Chemistry",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.pdf,
                    mimeType = "pdf"

                ),

                TaskItem(
                    id = 4,
                    title = "Homework 02",
                    subTitle = "Biology",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.docx,
                    mimeType = "docx"
                ),

                TaskItem(
                    id = 5,
                    title = "Homework 02",
                    subTitle = "History",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.png,
                    mimeType = "png"
                ),

                TaskItem(
                    id = 6,
                    title = "Homework 01",
                    subTitle = "Programming",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.jpg,
                    mimeType = "jpg"

                ),
            )
        }

        fun getTaskDataFilter(filter: String): List<TaskItem> {
            val data = arrayListOf(
                TaskItem(
                    id = 1,
                    title = "Homework 04",
                    subTitle = "Biology",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.docx,
                    mimeType = "docx"
                ),

                TaskItem(
                    id = 2,
                    title = "Homework 03",
                    subTitle = "History",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.pdf,
                    mimeType = "pdf"

                ),

                TaskItem(
                    id = 3,
                    title = "Homework 03",
                    subTitle = "Chemistry",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.pdf,
                    mimeType = "pdf"

                ),

                TaskItem(
                    id = 4,
                    title = "Homework 02",
                    subTitle = "Biology",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.docx,
                    mimeType = "docx"
                ),

                TaskItem(
                    id = 5,
                    title = "Homework 02",
                    subTitle = "History",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.png,
                    mimeType = "png"
                ),

                TaskItem(
                    id = 6,
                    title = "Homework 01",
                    subTitle = "Programming",
                    date = "2019/08/07",
                    status = "Finished",
                    image = R.drawable.jpg,
                    mimeType = "jpg"

                ),
            )
            data.filter { taskItem ->
                taskItem.mimeType == filter.toLowerCase(Locale.ROOT) ||
                        taskItem.subTitle == filter
            }
            return data
        }
    }
}