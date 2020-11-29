package com.cheise_proj.domain.model

data class StudentAttendance(
    val classId: String,
    val createdOn: String,
    val group: String,
    val id: Int,
    val name: String,
    val period: Int,
    val periodFrom: Int,
    val periodTo: Int,
    val remarks: String,
    val status: String,
    val studentId: String,
    val subject: String
)
