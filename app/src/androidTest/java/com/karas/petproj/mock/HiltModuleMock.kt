package com.karas.petproj.mock

import com.karas.petproj.datasources.UserLocalDataSource
import com.karas.petproj.db.di.DBModule
import com.karas.petproj.db.utils.UserLocalDataSourceContract
import com.karas.petproj.di.AppModule
import com.karas.petproj.repository.YouTRepository
import com.karas.petproj.repository.YouTRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

//@Module
//@TestInstallIn(components = [SingletonComponent::class], replaces = [DBModule::class, AppModule::class])
class HiltModuleMock {

//    @Provides
//    fun provideYoutDataSource(): UserLocalDataSourceContract {
//        return MockUserLocalDataSource()
//    }
//
//    @Provides
//    fun provideYouTRepository(userLocalDataSourceContract: UserLocalDataSourceContract) : YouTRepository {
//        return YouTRepositoryImpl(userLocalDataSourceContract)
//    }
}