package com.karas.petproj.db.di

import android.app.Application
import androidx.room.Room
import com.karas.petproj.datasources.UserLocalDataSource
import com.karas.petproj.db.AppDatabase
import com.karas.petproj.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
    ) = Room.databaseBuilder(app, AppDatabase::class.java, "meet_me_db")
        .build()

    @Provides
    fun provideModelDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao();
    }

    @Provides
    fun provideLocalDataSource(userDao: UserDao) : UserLocalDataSource {
        return UserLocalDataSource(userDao)
    }
}