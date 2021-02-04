package com.cheise_proj.core.feature.student.academics.task.vo

import com.cheise_proj.core.R

data class TaskSliderItem(
    val id: Int,
    val subject: String,
    val description: String,
    val teacherName: String,
    val teacherTitle: String,
    val date: String,
    val image: Int? = null
) {
    companion object {
        fun getTaskList(): List<TaskSliderItem> {
            return arrayListOf(
                TaskSliderItem(
                    id = 1,
                    subject = "Life Skills",
                    description = "Chapter 8 - Page 28",
                    teacherName = "Mavis Boateng",
                    teacherTitle = "LifeSkills Teacher",
                    date = "2019/08/08",
                    image = R.drawable.ic_baseline_account_circle_24
                ),
                TaskSliderItem(
                    id = 2,
                    subject = "Biology",
                    description = "Chapter 4 - Page 23",
                    teacherName = "Joesph Peprah",
                    teacherTitle = "Biology Teacher",
                    date = "2019/08/08",
                    image = R.drawable.ic_baseline_account_circle_24
                ),
                TaskSliderItem(
                    id = 3,
                    subject = "Programming",
                    description = "Chapter 14 - Page 33",
                    teacherName = "John Frimpong",
                    teacherTitle = "Java Teacher",
                    date = "2019/08/08",
                    image = R.drawable.ic_baseline_account_circle_24
                ),
                TaskSliderItem(
                    id = 4,
                    subject = "English",
                    description = "Chapter 1 - Page 2",
                    teacherName = "Frank Owusu",
                    teacherTitle = "English Teacher",
                    date = "2019/08/08",
                    image = R.drawable.ic_baseline_account_circle_24
                )
            )
        }

        fun getTask(position: Int): TaskSliderItem {
            return getTaskList()[position]
        }
    }
}