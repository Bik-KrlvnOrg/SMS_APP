package com.cheise_proj.infrastructure.remote.mapper

import com.cheise_proj.domain.model.FeesPaid
import com.cheise_proj.infrastructure.MapperList
import com.cheise_proj.infrastructure.remote.model.dto.FeesPaidDto

object FeesPaidMapper : MapperList<FeesPaidDto.FeesPaid, FeesPaid> {
    override fun mapTo(t: FeesPaidDto.FeesPaid): FeesPaid {
        return FeesPaid(
            id = t.id,
            classId = t.classId,
            toDate = t.toDate,
            fromDate = t.fromDate,
            waived = t.waived,
            voucherEntryId = t.voucherEntryId,
            studentId = t.studentId,
            particularName = t.particularName,
            particularId = t.particularId,
            installment = t.installment,
            feeAmount = t.feeAmount,
            createdDate = t.createdDate,
            comments = t.comments,
            academicYear = t.academicYear
        )
    }

    override fun mapFrom(e: FeesPaid): FeesPaidDto.FeesPaid {
        return FeesPaidDto.FeesPaid(
            id = e.id,
            classId = e.classId,
            toDate = e.toDate,
            fromDate = e.fromDate,
            waived = e.waived,
            voucherEntryId = e.voucherEntryId,
            studentId = e.studentId,
            particularName = e.particularName,
            particularId = e.particularId,
            installment = e.installment,
            feeAmount = e.feeAmount,
            createdDate = e.createdDate,
            comments = e.comments,
            academicYear = e.academicYear
        )
    }

    override fun mapToList(tList: List<FeesPaidDto.FeesPaid>): List<FeesPaid> {
        val data = arrayListOf<FeesPaid>()
        tList.forEach {
            data.add(mapTo(it))
        }
        return data
    }

    override fun mapFromList(eList: List<FeesPaid>): List<FeesPaidDto.FeesPaid> {
        val data = arrayListOf<FeesPaidDto.FeesPaid>()
        eList.forEach {
            data.add(mapFrom(it))
        }
        return data
    }
}

internal fun List<FeesPaid>.mapDomainListFrom() = FeesPaidMapper.mapFromList(this)
internal fun List<FeesPaidDto.FeesPaid>.mapDtoListTo() = FeesPaidMapper.mapToList(this)