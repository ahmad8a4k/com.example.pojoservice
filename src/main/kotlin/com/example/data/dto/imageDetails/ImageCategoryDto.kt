package com.example.data.dto.imageDetails

import kotlinx.serialization.SerialName

data class ImageCategoryDto(
    @SerialName("category_id") val category_id: Int,
    @SerialName("category_name") val category_name: String,
    @SerialName("category_url") val category_url: String,
    @SerialName("admin") val admin: ImageCategoryAdminDto,
    @SerialName("category_date") val category_date: String,
)

data class ImageCategoryAdminDto(
    @SerialName("admin_id") val admin_id: Int,
    @SerialName("admin_name") val admin_name: String,
)