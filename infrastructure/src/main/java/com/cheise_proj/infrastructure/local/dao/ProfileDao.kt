package com.cheise_proj.infrastructure.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheise_proj.infrastructure.local.entity.StudentEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudent(studentEntity: StudentEntity)

    @Query("SELECT * FROM student")
    fun getStudentProfile(): Observable<StudentEntity>
}