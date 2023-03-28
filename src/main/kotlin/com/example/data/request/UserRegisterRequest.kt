package com.example.data.request

data class UserRegisterRequest(
    val userEmail: String,
    val username: String,
    val password: String,
)