package com.example.data.dto.imageDetails

import kotlinx.serialization.SerialName

data class ImageDetailsFullDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_Title") val image_Title: String,
    @SerialName("image_url") val image_url: String,
    @SerialName("category") val category: ImageDetailsCategoryDto,
    @SerialName("color") val color: ImageDetailsColorDto,
    @SerialName("category_details") val category_details: ImageDetailsCategoryDetailsDto,
    @SerialName("image_description") val image_description: String,
    @SerialName("admin") val admin: ImageDetailsAdminDto,
    @SerialName("image_Stats_details") val image_Stats_details: ImageDetailsImageStatsDetailsDto,
    @SerialName("register") val register: String,
)

data class ImageDetailsColorDto(
    @SerialName("color_id") val color_id: Int,
    @SerialName("color_name") val color_name: String,
)

data class ImageDetailsCategoryDto(
    @SerialName("category_id") val category_id: Int,
    @SerialName("category_name") val category_name: String,
    @SerialName("category_url") val category_url: String,
)

data class ImageDetailsCategoryDetailsDto(
    @SerialName("category_details_id") val category_details_id: Int,
    @SerialName("category_details_name") val category_details_name: String,
)

data class ImageDetailsAdminDto(
    @SerialName("admin_id") val admin_id: Int,
    @SerialName("admin_name") val admin_name: String,
)

data class ImageDetailsImageStatsDetailsDto(
    @SerialName("id") val stats_id: Int,
    @SerialName("like_numbers") val like_numbers: Int,
    @SerialName("watch_numbers") val watch_numbers: Int,
    @SerialName("download_numbers") val download_numbers: Int,
    @SerialName("save_numbers") val save_numbers: Int,
    @SerialName("share_numbers") val share_numbers: Int
)