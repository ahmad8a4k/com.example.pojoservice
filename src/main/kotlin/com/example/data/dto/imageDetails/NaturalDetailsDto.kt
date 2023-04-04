package com.example.data.dto.imageDetails

import kotlinx.serialization.SerialName

data class NaturalDetailsDto(
    @SerialName("natural_id") val natural_id: Int,
    @SerialName("natural_image_title") val image_Title: String,
    @SerialName("natural_url") val natural_url: String,
    @SerialName("natural_category") val natural_category: NaturalCategoriesDto,
    @SerialName("color") val color: ImageDetailsColorDto,
    @SerialName("admin") val admin: ImageDetailsAdminDto,
    @SerialName("register") val register: String,
)