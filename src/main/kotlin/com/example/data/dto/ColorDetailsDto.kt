package com.example.data.dto

import kotlinx.serialization.SerialName

data class ColorDetailsDto(
    @SerialName("color_id") val color_id: Int,
    @SerialName("color_name") val color_name:String,
    @SerialName("color_hex") val color_hex :String,
)