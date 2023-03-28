package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UserNameAndPasswordRequest(
    val username :String,
    val password:String
)