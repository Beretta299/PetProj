package com.karas.petproj.network.di

import com.karas.petproj.network.FireStoreImplementation
import com.karas.petproj.network.FireStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideFireStoreRepository() : FireStoreRepository{
        return FireStoreImplementation()
    }
}