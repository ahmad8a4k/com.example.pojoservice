package com.example.data.dto

import com.example.data.dto.imageDetails.*
import kotlinx.serialization.SerialName

data class LiteImageDetailsDto(
    @SerialName("image_id") val image_id: Int,
    @SerialName("image_Title") val image_Title: String,
    @SerialName("image_url") val image_url: String,
    @SerialName("image_description") val image_description: String,
    @SerialName("register") val register: String,
)