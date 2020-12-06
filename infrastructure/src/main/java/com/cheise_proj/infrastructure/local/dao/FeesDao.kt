package com.cheise_proj.infrastructure.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheise_proj.infrastructure.local.entity.FeesPaidEntity
import io.reactivex.Observable

@Dao
interface FeesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFeesPaid(feesPaidList: List<FeesPaidEntity>)

    @Query("SELECT * FROM fees_paid")
    fun getFeesPaid(): Observable<List<FeesPaidEntity>>
}