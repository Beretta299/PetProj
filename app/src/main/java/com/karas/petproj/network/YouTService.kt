package com.karas.petproj.network

import com.karas.petproj.model.Statistics
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTService {


    @GET("videos?part=snippet%2CcontentDetails%2Cstatistics")
    suspend fun getVideoStatistic(@Query("key")
                                      key: String = "", @Query("id") videoId: String): Response<Statistics>
}