package com.cheise_proj.infrastructure.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheise_proj.infrastructure.local.dao.ProfileDao
import com.cheise_proj.infrastructure.local.dao.UserDao
import com.cheise_proj.infrastructure.local.entity.StudentEntity
import com.cheise_proj.infrastructure.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        StudentEntity::class
    ],
    version = 2,
    exportSchema = false
)

abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun profileDao(): ProfileDao

    companion object {
        val DATABASE_NAME = "local.db"
    }
}