package com.cheise_proj.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.cheise_proj.core.data.local.entity.StudentEntity

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudent(studentEntity:StudentEntity)
}