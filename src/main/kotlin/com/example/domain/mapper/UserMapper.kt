package com.example.domain.mapper

import com.example.data.dto.UserDto
import com.example.data.entities.UserEntity

fun UserEntity.toUserDto() = UserDto(
    user_id = userId,
    user_name = username,
    user_password = userPassword,
    user_email = email,
    user_salt = userSalt
)