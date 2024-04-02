package com.karas.petproj.repository

import com.karas.petproj.model.Statistics
import com.karas.petproj.network.YouTService
import javax.inject.Inject

interface YouTRepository {

    suspend fun getStatistic(videoID: String) : Statistics?
}


class YouTRepositoryImpl(private val youTService: YouTService) : YouTRepository {
    override suspend fun getStatistic(videoID: String): Statistics? {
        return youTService.getVideoStatistic(videoId = videoID).body()
    }
}