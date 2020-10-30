package com.cheise_proj.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val address: String,
    val admissionDate: String,
    val admissionId: String,
    val admissionStatus: String,
    val admissionType: String,
    val age: Int,
    val avatarUrl: String,
    val bloodGroup: String,
    val city: String,
    val classId: String,
    val contact: String,
    val country: String,
    val dob: String,
    val email: String,
    val fatherName: String,
    val firstName: String,
    val fromDate: String,
    val gender: String,
    val lastName: String,
    val middleName: String,
    val motherName: String,
    val nationality: String,
    val prevSchoolDate: String,
    val religion: String,
    val residentialAddress: String,
    val resultStatus: String,
    val schoolId: Int,
    val smsNo: String,
    val status: String,
    val title: String,
    val toDate: String,
    val username: String
)
