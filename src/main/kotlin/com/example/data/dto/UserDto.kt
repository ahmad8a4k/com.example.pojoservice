package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("user_id") val user_id: Int = 0,
    @SerialName("user_name") val user_name: String = "Empty",
    @SerialName("full_name") val full_name: String = "Empty",
    @SerialName("user_email") val user_email: String = "Empty",
    @SerialName("user_password") val user_password: String = "Empty",
    @SerialName("user_salt") val user_salt: String = "Empty",
)

@Serializable
data class UserLiteDto(
    @SerialName("user_id") val user_id: Int = 0,
)