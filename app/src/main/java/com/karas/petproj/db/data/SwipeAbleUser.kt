package com.karas.petproj.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karas.petproj.db.DBContract

@Entity(tableName = DBContract.SwipeAbleUser.TABLE_NAME)
data class SwipeAbleUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBContract.SwipeAbleUser.USER_ID)
    var id: Int? = null,
    @ColumnInfo(name = DBContract.SwipeAbleUser.USER_NAME)
    var name: String,
    @ColumnInfo(name = DBContract.SwipeAbleUser.USER_IMAGE)
    var image: String,
    var swipedState: Int = 0
)
