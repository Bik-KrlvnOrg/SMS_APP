package com.cheise_proj.infrastructure.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fees_paid")
data class FeesPaidEntity(
 @PrimaryKey(autoGenerate = false)
 val id: Int,
 val academicYear: String,
 val classId: String,
 val comments: String,
 val createdDate: String,
 val feeAmount: Int,
 val fromDate: String,
 val installment: Int,
 val particularId: Int,
 val particularName: String,
 val studentId: Int,
 val toDate: String,
 val voucherEntryId: Int,
 val waived: String
)
