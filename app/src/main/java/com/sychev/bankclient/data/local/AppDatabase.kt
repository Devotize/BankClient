package com.sychev.bankclient.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sychev.bankclient.data.local.dao.UserDao
import com.sychev.bankclient.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}