package com.cheise_proj.core.data.remote.model.dto


import com.google.gson.annotations.SerializedName

data class StudentDto(
    @SerializedName("studentProfile")
    val studentProfile: StudentProfileDto
) {
    data class StudentProfileDto(
        @SerializedName("address")
        val address: String,
        @SerializedName("admissionDate")
        val admissionDate: String,
        @SerializedName("admissionId")
        val admissionId: String,
        @SerializedName("admissionStatus")
        val admissionStatus: String,
        @SerializedName("admissionType")
        val admissionType: String,
        @SerializedName("age")
        val age: Int,
        @SerializedName("avatarUrl")
        val avatarUrl: String,
        @SerializedName("bloodGroup")
        val bloodGroup: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("classId")
        val classId: String,
        @SerializedName("contact")
        val contact: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("dob")
        val dob: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("fatherName")
        val fatherName: String,
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("fromDate")
        val fromDate: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("lastName")
        val lastName: String,
        @SerializedName("middleName")
        val middleName: String,
        @SerializedName("motherName")
        val motherName: String,
        @SerializedName("nationality")
        val nationality: String,
        @SerializedName("prevSchool")
        val prevSchool: String,
        @SerializedName("prevSchoolDate")
        val prevSchoolDate: String,
        @SerializedName("religion")
        val religion: String,
        @SerializedName("residentialAddress")
        val residentialAddress: String,
        @SerializedName("resultStatus")
        val resultStatus: String,
        @SerializedName("schoolId")
        val schoolId: Int,
        @SerializedName("smsNo")
        val smsNo: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("toDate")
        val toDate: String,
        @SerializedName("username")
        val username: String
    )
}