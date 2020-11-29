package com.cheise_proj.infrastructure.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheise_proj.infrastructure.local.entity.UserEntity
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userEntity: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Single<UserEntity>

    @Query("UPDATE user SET accessToken = :newAccessToken,refreshToken = :newRefreshToken WHERE username = :username")
    fun updateTokens(username: String?, newAccessToken: String, newRefreshToken: String)
}