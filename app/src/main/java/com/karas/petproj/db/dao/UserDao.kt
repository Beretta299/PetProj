package com.karas.petproj.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karas.petproj.db.DBContract
import com.karas.petproj.db.data.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(userEntity: UserEntity)

    @Query("SELECT COUNT(*) FROM ${DBContract.UserEntity.TABLE_NAME}")
    suspend fun getUserCount(): Int

    @Query("SELECT * FROM ${DBContract.UserEntity.TABLE_NAME}")
    suspend fun getUserList(): List<UserEntity>


}