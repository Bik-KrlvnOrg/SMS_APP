package com.cheise_proj.infrastructure.local.mapper

import com.cheise_proj.domain.model.FeesPaid
import com.cheise_proj.infrastructure.MapperList
import com.cheise_proj.infrastructure.local.entity.FeesPaidEntity

object FeesPaidMapper : MapperList<FeesPaidEntity, FeesPaid> {
    override fun mapTo(t: FeesPaidEntity): FeesPaid {
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

    override fun mapFrom(e: FeesPaid): FeesPaidEntity {
        return FeesPaidEntity(
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

    override fun mapToList(tList: List<FeesPaidEntity>): List<FeesPaid> {
        val data = arrayListOf<FeesPaid>()
        tList.forEach {
            data.add(mapTo(it))
        }
        return data
    }

    override fun mapFromList(eList: List<FeesPaid>): List<FeesPaidEntity> {
        val data = arrayListOf<FeesPaidEntity>()
        eList.forEach {
            data.add(mapFrom(it))
        }
        return data
    }
}

internal fun List<FeesPaid>.mapFromList() = FeesPaidMapper.mapFromList(this)
internal fun List<FeesPaidEntity>.mapToList() = FeesPaidMapper.mapToList(this)