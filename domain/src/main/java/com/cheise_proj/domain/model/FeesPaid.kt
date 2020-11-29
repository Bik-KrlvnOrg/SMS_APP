package com.cheise_proj.domain.model

data class FeesPaid(
    val academicYear: String,
    val classId: String,
    val comments: String,
    val createdDate: String,
    val feeAmount: Int,
    val fromDate: String,
    val id: Int,
    val installment: Int,
    val particularId: Int,
    val particularName: String,
    val studentId: Int,
    val toDate: String,
    val voucherEntryId: Int,
    val waived: String
)
