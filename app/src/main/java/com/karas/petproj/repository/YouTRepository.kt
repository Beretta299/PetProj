package com.karas.petproj.repository

import com.karas.petproj.db.data.UserEntity
import com.karas.petproj.db.utils.UserLocalDataSourceContract
import kotlinx.coroutines.delay

interface YouTRepository {
    suspend fun hasUserInSystem(): Boolean

    suspend fun addUser(user: UserEntity)

    suspend fun getUsersList() : List<UserEntity>
}


class YouTRepositoryImpl(private val userLocalDataSource: UserLocalDataSourceContract) : YouTRepository {
    override suspend fun hasUserInSystem(): Boolean {
        return userLocalDataSource.hasUser()
    }

    override suspend fun addUser(user: UserEntity) {
        userLocalDataSource.insertUser(user)
    }

    override suspend fun getUsersList(): List<UserEntity> {
        return userLocalDataSource.getUsers()
    }
}