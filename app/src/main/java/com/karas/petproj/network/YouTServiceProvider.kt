package com.karas.petproj.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object YouTServiceProvider {
    private const val BASE_URL = "https://youtube.googleapis.com/youtube/v3/"

    fun getClient() : YouTService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YouTService::class.java)
    }
}