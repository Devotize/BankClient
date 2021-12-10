package com.sychev.bankclient.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sychev.bankclient.data.local.dao.UserDao
import com.sychev.bankclient.data.local.entity.UserEntity
import com.sychev.bankclient.data.local.mapper.Converters

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}