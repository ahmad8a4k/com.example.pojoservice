package com.example.data.request

data class UserRegisterRequest(
    val userEmail: String,
    val username: String,
    val fullName:String,
    val password: String,
)