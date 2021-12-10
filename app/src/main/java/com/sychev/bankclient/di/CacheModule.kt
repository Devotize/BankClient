package com.sychev.bankclient.di

import android.content.Context
import androidx.room.Room
import com.sychev.bankclient.data.local.AppDatabase
import com.sychev.bankclient.data.local.mapper.UserEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "bank_client_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDb: AppDatabase) = appDb.userDao()

    @Singleton
    @Provides
    fun provideUserEntityMapper() = UserEntityMapper()
}