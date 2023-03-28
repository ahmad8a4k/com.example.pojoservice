package com.example.domain.mapper

import com.example.data.dto.UserDto
import com.example.data.entities.UserEntity

fun UserEntity.toUserDto() = UserDto(
    userId = userId,
    userName = username,
    userPassword = userPassword,
    email = email,
    userSalt = userSalt
)