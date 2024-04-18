package com.karas.petproj.db

object DBContract {
    object UserEntity {
        const val TABLE_NAME = "user_entity"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "user_email"
        const val USER_PASSWORD = "user_password"
    }

    object SwipeAbleUser {
        const val TABLE_NAME = "swipe_able_user_entity"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_IMAGE = "user_image"
    }
}