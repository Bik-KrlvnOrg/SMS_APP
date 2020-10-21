package com.cheise_proj.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheise_proj.core.data.local.dao.UserDao
import com.cheise_proj.core.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME = "local.db"
    }
}