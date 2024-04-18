package com.karas.petproj.db.utils

import com.karas.petproj.db.data.UserEntity

interface UserLocalDataSourceContract {
    suspend fun insertUser(user: UserEntity)

    suspend fun hasUser() : Boolean

    suspend fun getUsers(): List<UserEntity>
}