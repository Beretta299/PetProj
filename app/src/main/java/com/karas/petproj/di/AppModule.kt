package com.karas.petproj.di

import com.karas.petproj.datasources.UserLocalDataSource
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