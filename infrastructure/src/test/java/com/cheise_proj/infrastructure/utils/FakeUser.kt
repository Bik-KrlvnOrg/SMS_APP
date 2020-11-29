package com.cheise_proj.infrastructure.utils

import com.cheise_proj.infrastructure.local.entity.StudentEntity
import com.cheise_proj.infrastructure.remote.model.dto.StudentDto

object FakeUser {
    fun getStudentProfile():StudentEntity{
        return StudentEntity(
            id = 1,
            title = "mr/miss",
            status = "any status",
            smsNo = "any sms number",
            schoolId = 1,
            resultStatus = "",
            residentialAddress = "",
            religion = "",
            prevSchoolDate = "",
            nationality = "",
            motherName = "",
            middleName = "",
            lastName = "any last name",
            gender = "any gender",
            firstName = "any first name",
            fatherName = "",
            email = "",
            dob = "",
            country = "",
            contact = "",
            city = "",
            bloodGroup = "",
            avatarUrl = "",
            age = 1,
            admissionType = "",
            admissionStatus = "",
            admissionId = "",
            admissionDate = "",
            address = "",
            fromDate = "",
            toDate = "",
            classId = "",
            username = "any username",
        )
    }
    fun getStudentDto(): StudentDto {
        return StudentDto(
            studentProfile = StudentDto.StudentProfileDto(
                id = 1,
                title = "mr/miss",
                status = "any status",
                smsNo = "any sms number",
                schoolId = 1,
                resultStatus = "",
                residentialAddress = "",
                religion = "",
                prevSchoolDate = "",
                nationality = "",
                motherName = "",
                middleName = "",
                lastName = "any last name",
                gender = "any gender",
                firstName = "any first name",
                fatherName = "",
                email = "",
                dob = "",
                country = "",
                contact = "",
                city = "",
                bloodGroup = "",
                avatarUrl = "",
                age = 1,
                admissionType = "",
                admissionStatus = "",
                admissionId = "",
                admissionDate = "",
                address = "",
                fromDate = "",
                toDate = "",
                classId = "",
                username = "any username",
                prevSchool = ""
            )
        )
    }
}