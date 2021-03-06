package com.cheise_proj.infrastructure.local.mapper

import com.cheise_proj.domain.model.Student
import com.cheise_proj.infrastructure.Mapper
import com.cheise_proj.infrastructure.local.entity.StudentEntity

object StudentMapper : Mapper<Student, StudentEntity> {
    override fun mapTo(t: Student): StudentEntity {
        return StudentEntity(
            id = t.id,
            username = t.username,
            email = t.email,
            title = t.title,
            gender = t.gender,
            dob = t.dob,
            bloodGroup = t.bloodGroup,
            address = t.address,
            contact = t.contact,
            schoolId = t.schoolId,
            status = t.status,
            avatarUrl = t.avatarUrl,
            admissionDate = t.admissionDate,
            admissionId = t.admissionId,
            admissionStatus = t.admissionStatus,
            admissionType = t.admissionType,
            age = t.age,
            city = t.city,
            classId = t.classId,
            country = t.country,
            fatherName = t.fatherName,
            firstName = t.firstName,
            fromDate = t.fromDate,
            lastName = t.lastName,
            middleName = t.middleName,
            motherName = t.motherName,
            nationality = t.nationality,
            prevSchoolDate = t.prevSchoolDate,
            religion = t.religion,
            residentialAddress = t.residentialAddress,
            resultStatus = t.resultStatus,
            smsNo = t.smsNo,
            toDate = t.toDate
        )
    }

    override fun mapFrom(e: StudentEntity): Student {
        return Student(
            id = e.id,
            username = e.username,
            email = e.email,
            title = e.title,
            gender = e.gender,
            dob = e.dob,
            bloodGroup = e.bloodGroup,
            address = e.address,
            contact = e.contact,
            schoolId = e.schoolId,
            status = e.status,
            avatarUrl = e.avatarUrl,
            admissionDate = e.admissionDate,
            admissionId = e.admissionId,
            admissionStatus = e.admissionStatus,
            admissionType = e.admissionType,
            age = e.age,
            city = e.city,
            classId = e.classId,
            country = e.country,
            fatherName = e.fatherName,
            firstName = e.firstName,
            fromDate = e.fromDate,
            lastName = e.lastName,
            middleName = e.middleName,
            motherName = e.motherName,
            nationality = e.nationality,
            prevSchoolDate = e.prevSchoolDate,
            religion = e.religion,
            residentialAddress = e.residentialAddress,
            resultStatus = e.resultStatus,
            smsNo = e.smsNo,
            toDate = e.toDate
        )
    }
}

internal fun Student.mapEntityTo() = StudentMapper.mapTo(this)
internal fun StudentEntity.mapFrom() = StudentMapper.mapFrom(this)