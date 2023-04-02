package com.example.data.dto

import kotlinx.serialization.SerialName
import java.util.*

data class ImageDetailsDto(
    @SerialName("id") val id: Int,
    @SerialName("imgTitle") val imgTitle: String,
    @SerialName("url") val url: String,
    @SerialName("categoryId") val categoryId: Int,
    @SerialName("colorId") val colorId: Int,
    @SerialName("categoryDetailsId") val categoryDetailsId: Int,
    @SerialName("imgDescription") val imgDescription: String,
    @SerialName("adminId") val adminId: Int,
    @SerialName("register") val register: String,
)