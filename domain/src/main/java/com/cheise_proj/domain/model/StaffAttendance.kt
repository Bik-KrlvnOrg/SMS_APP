package com.cheise_proj.domain.model

data class StaffAttendance(
    val createdOn: String,
    val departmentId: String,
    val designationId: String,
    val id: Int,
    val name: String,
    val remarks: String,
    val staffId: String,
    val status: String
)
