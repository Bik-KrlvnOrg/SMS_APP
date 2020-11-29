package utils

import com.cheise_proj.domain.model.Student

object FakeUser {
    fun getStudentProfile(): Student {
        return Student(
            id = 1,
            username = "any username",
            classId = "",
            toDate = "any date",
            fromDate = "any from date",
            address = "any address",
            admissionDate = "",
            admissionId = "",
            admissionStatus = "",
            admissionType = "",
            age = 1,
            avatarUrl = "",
            bloodGroup = "",
            city = "",
            contact = "",
            country = "",
            dob = "",
            email = "",
            fatherName = "",
            firstName = "",
            gender = "",
            lastName = "any last name",
            middleName = "",
            motherName = "",
            nationality = "",
            prevSchoolDate = "",
            religion = "",
            residentialAddress = "",
            resultStatus = "",
            schoolId = 1,
            smsNo = "",
            status = "",
            title = "mr/miss"
        )
    }
}