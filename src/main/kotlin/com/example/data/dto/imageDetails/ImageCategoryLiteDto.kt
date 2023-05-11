package com.example.data.dto.imageDetails

import kotlinx.serialization.SerialName

data class ImageCategoryLiteDto(
    @SerialName("category_id") val category_id: Int,
    @SerialName("category_name") val category_name: String,
    @SerialName("category_url") val category_url: String,
    @SerialName("category_blur_hash") val category_blur_hash: String,
)