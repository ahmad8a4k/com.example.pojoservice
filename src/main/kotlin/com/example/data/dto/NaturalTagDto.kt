package com.example.data.dto

import kotlinx.serialization.SerialName

data class NaturalTagDto(
    @SerialName("tag_id") val tag_id:String,
    @SerialName("tag_name") val tag_name:String
)