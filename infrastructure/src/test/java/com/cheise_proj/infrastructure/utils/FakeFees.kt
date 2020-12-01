package com.cheise_proj.infrastructure.utils

import com.cheise_proj.infrastructure.local.entity.FeesPaidEntity
import com.cheise_proj.infrastructure.remote.model.dto.FeesPaidDto

internal object FakeFees {
    fun getFeesPaidDto(): FeesPaidDto {
        return FeesPaidDto(
            feesPaid =
            arrayListOf(
                FeesPaidDto.FeesPaid(
                    id = 1,
                    academicYear = "any year",
                    comments = "any comment",
                    createdDate = "any date",
                    feeAmount = 100,
                    installment = 1,
                    particularId = 1,
                    particularName = "any particular name",
                    studentId = 1,
                    voucherEntryId = 1,
                    waived = "",
                    fromDate = "any date",
                    toDate = "any date",
                    classId = "1"
                )
            )

        )
    }

    fun getFeesPaidEntity(): List<FeesPaidEntity> {
        return arrayListOf(
            FeesPaidEntity(
                id = 1,
                academicYear = "any year",
                comments = "any comment",
                createdDate = "any date",
                feeAmount = 100,
                installment = 1,
                particularId = 1,
                particularName = "any particular name",
                studentId = 1,
                voucherEntryId = 1,
                waived = "",
                fromDate = "any date",
                toDate = "any date",
                classId = "1"
            )
        )
    }
}