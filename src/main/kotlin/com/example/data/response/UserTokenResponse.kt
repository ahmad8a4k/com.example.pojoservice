package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
data class UserTokenResponse(
    val token: String? = "",
)