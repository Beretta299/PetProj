package com.karas.petproj.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karas.petproj.db.dao.UserDao
import com.karas.petproj.db.data.UserEntity

@Database(entities = [UserEntity::class],
    version = 1,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        //Migrations
    }
}