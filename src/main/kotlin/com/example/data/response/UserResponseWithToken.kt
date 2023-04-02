package com.example.data.response

import com.example.data.dto.UserDto

data class UserResponseWithToken(
    val user: UserDto?= UserDto(),
    val token: String? = "",
)