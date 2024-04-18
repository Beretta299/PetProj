package com.karas.petproj.di

import android.app.Application
import androidx.room.Room
import com.karas.petproj.datasources.UserLocalDataSource
import com.karas.petproj.db.AppDatabase
import com.karas.petproj.db.dao.UserDao
import com.karas.petproj.network.YouTService
import com.karas.petproj.network.YouTServiceProvider
import com.karas.petproj.repository.YouTRepository
import com.karas.petproj.repository.YouTRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYouTRepository(userLocalDataSource: UserLocalDataSource): YouTRepository {
        return YouTRepositoryImpl(userLocalDataSource)
    }
}