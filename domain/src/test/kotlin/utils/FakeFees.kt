package utils

import com.cheise_proj.domain.model.FeesPaid

internal object FakeFees {
    fun getFeesPaid(): List<FeesPaid> {
        return arrayListOf(
            FeesPaid(
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