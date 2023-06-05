package com.example.data.dto

import kotlinx.serialization.SerialName

data class LiteImageDetailsDtoDeplecated(
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

data class LiteImageDetailsDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_url") val image_url: String,
    @SerialName("likes_count") val likes_count: Int,
    @SerialName("blur_hash") val blur_hash: String,
    @SerialName("color_id") val color_id: Int,
    @SerialName("category_id") val category_id: Int,
    @SerialName("color_hex") val color_hex: String,
)

data class ImageDetailsWithLikesAndWatchAndUser(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_url") val image_url: String,
    @SerialName("image_title") val image_title: String,
    @SerialName("image_description") val image_description: String,
    @SerialName("likes_count") val like_count: Int,
    @SerialName("watch_count") val watch_count: Int,
    @SerialName("blur_hash") val blur_hash: String,
    @SerialName("user_id") val user_id: Int,
    @SerialName("user_name") val user_name: String,
    @SerialName("user_url") val user_url: String,
)

data class LiteImageDetailsWithLiteUserInformationDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_url") val image_url: String,
    @SerialName("likes_count") val likes_count: Int,
    @SerialName("blur_hash") val blur_hash: String,
    @SerialName("color_id") val color_id: Int,
    @SerialName("category_id") val category_id: Int,
    @SerialName("color_hex") val color_hex: String,
    @SerialName("user_id") val user_id: Int,
    @SerialName("user_name") val user_name: String,
    @SerialName("user_url") val user_url: String,
)

data class IdAndUrlImagesWithDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_url") val image_url: String,
    @SerialName("blur_hash") val blur_hash: String,
)