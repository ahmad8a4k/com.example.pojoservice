package com.example.data.dto

import kotlinx.serialization.SerialName

data class LiteImageDetailsDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_Title") val image_Title: String,
    @SerialName("image_url") val image_url: String,
)

data class LiteImageDetailsWithLikesCountAndTitleDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_Title") val image_Title: String,
    @SerialName("image_url") val image_url: String,
    @SerialName("likes_count") val likes_count: Int,
)

data class LiteImageDetailsWithLikesCountDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_url") val image_url: String,
    @SerialName("likes_count") val likes_count: Int,
)