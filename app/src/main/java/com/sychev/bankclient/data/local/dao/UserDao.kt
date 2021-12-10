package com.sychev.bankclient.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sychev.bankclient.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users: UserEntity): LongArray

}