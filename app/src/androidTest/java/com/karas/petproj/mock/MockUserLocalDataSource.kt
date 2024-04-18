package com.karas.petproj.mock

import com.karas.petproj.db.data.UserEntity
import com.karas.petproj.db.utils.UserLocalDataSourceContract
import kotlinx.coroutines.delay

class MockUserLocalDataSource : UserLocalDataSourceContract {
    var list = arrayListOf<UserEntity>()

    override suspend fun insertUser(user: UserEntity) {
        delay(1000)
        list.add(user)
    }

    override suspend fun hasUser(): Boolean {
        return list.size > 0
    }

    override suspend fun getUsers(): List<UserEntity> {
        delay(10_000)
        return list
    }
}