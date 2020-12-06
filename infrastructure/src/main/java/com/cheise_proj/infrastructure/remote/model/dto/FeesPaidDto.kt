package com.cheise_proj.infrastructure.remote.model.dto


import com.google.gson.annotations.SerializedName

data class FeesPaidDto(
    @SerializedName("data")
    val feesPaid: List<FeesPaid>
) {
    data class FeesPaid(
        @SerializedName("academicYear")
        val academicYear: String,
        @SerializedName("classId")
        val classId: String,
        @SerializedName("comments")
        val comments: String,
        @SerializedName("createdDate")
        val createdDate: String,
        @SerializedName("feeAmount")
        val feeAmount: Int,
        @SerializedName("fromDate")
        val fromDate: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("installment")
        val installment: Int,
        @SerializedName("particularId")
        val particularId: Int,
        @SerializedName("particulartName")
        val particularName: String,
        @SerializedName("studentId")
        val studentId: Int,
        @SerializedName("toDate")
        val toDate: String,
        @SerializedName("voucherEntryId")
        val voucherEntryId: Int,
        @SerializedName("waived")
        val waived: String
    )
}