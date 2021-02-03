package com.cheise_proj.core.feature.student.academics.examination.vo

data class ExaminationScore(
    val id: Int,
    val subject: String,
    val marks: String,
    val total: String,
    val grade: String
)