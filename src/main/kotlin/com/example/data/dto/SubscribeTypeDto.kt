package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SubscribeTypeDto(
    @SerialName("subscribe_id") val subscrib_id: Int = 1,
    @SerialName("subscribe_type_name") val subscribe_type_name: String = "Empty"
)