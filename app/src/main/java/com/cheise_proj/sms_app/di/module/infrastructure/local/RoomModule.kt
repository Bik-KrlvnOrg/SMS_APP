package com.cheise_proj.sms_app.di.module.infrastructure.local

import android.app.Application
import androidx.room.Room
import com.cheise_proj.infrastructure.local.LocalDatabase
import com.cheise_proj.infrastructure.local.LocalDatabase.Companion.DATABASE_NAME
import com.cheise_proj.infrastructure.local.dao.FeesDao
import com.cheise_proj.infrastructure.local.dao.ProfileDao
import com.cheise_proj.infrastructure.local.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RoomModule.Binders::class])
class RoomModule {
    @Module
    interface Binders

    @Singleton
    @Provides
    fun provideRoomDb(application: Application): LocalDatabase {
        return Room
            .databaseBuilder(
                application.applicationContext,
                LocalDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFeesDao(localDatabase: LocalDatabase): FeesDao = localDatabase.feesDao()

    @Singleton
    @Provides
    fun provideUserDao(localDatabase: LocalDatabase): UserDao = localDatabase.userDao()

    @Singleton
    @Provides
    fun provideProfileDao(localDatabase: LocalDatabase): ProfileDao = localDatabase.profileDao()

}