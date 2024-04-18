package com.karas.petproj.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karas.petproj.db.DBContract

@Entity(tableName = DBContract.UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBContract.UserEntity.USER_ID)
    var id: Int? = null,
    @ColumnInfo(name = DBContract.UserEntity.USER_NAME)
    var name: String,
    @ColumnInfo(name = DBContract.UserEntity.USER_EMAIL)
    var email: String,
    @ColumnInfo(name = DBContract.UserEntity.USER_PASSWORD)
    var password: String)
