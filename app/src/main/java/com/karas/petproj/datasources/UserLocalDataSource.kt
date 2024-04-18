package com.karas.petproj.datasources

import com.karas.petproj.db.dao.UserDao
import com.karas.petproj.db.data.UserEntity
import com.karas.petproj.db.utils.UserLocalDataSourceContract
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(val userDao: UserDao) : UserLocalDataSourceContract {

    override suspend fun insertUser(user: UserEntity) {
        userDao.registerUser(user)
    }

    override suspend fun hasUser() : Boolean {
        return userDao.getUserCount() > 0
    }

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getUserList()
    }
}