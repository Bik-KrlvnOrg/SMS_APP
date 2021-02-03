package com.cheise_proj.core.feature.student.academics.examination.vo

data class ExaminationItem(
    val id: Int,
    val title: String,
    val name: String,
    val actionId: Int? = null
)