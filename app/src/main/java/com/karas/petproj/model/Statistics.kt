package com.karas.petproj.model

data class Statistics(
    val etag: String = "",
    val items: List<Item> = listOf(),
    val kind: String = "",
    val pageInfo: PageInfo = PageInfo(0,0)
)

data class Item(
    val contentDetails: ContentDetails,
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet,
    val statistics: StatisticsX
)

data class PageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class ContentDetails(
    val caption: String,
    val contentRating: ContentRating,
    val definition: String,
    val dimension: String,
    val duration: String,
    val licensedContent: Boolean,
    val projection: String
)

data class Snippet(
    val categoryId: String,
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val localized: Localized,
    val publishedAt: String,
    val tags: List<String>,
    val thumbnails: Thumbnails,
    val title: String
)

data class StatisticsX(
    val commentCount: String,
    val favoriteCount: String,
    val likeCount: String,
    val viewCount: String
)

class ContentRating

data class Localized(
    val description: String,
    val title: String
)

data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxres,
    val medium: Medium,
    val standard: Standard
)

data class Default(
    val height: Int,
    val url: String,
    val width: Int
)

data class High(
    val height: Int,
    val url: String,
    val width: Int
)

data class Maxres(
    val height: Int,
    val url: String,
    val width: Int
)

data class Medium(
    val height: Int,
    val url: String,
    val width: Int
)

data class Standard(
    val height: Int,
    val url: String,
    val width: Int
)