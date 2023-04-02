package com.example.data.mapper

import com.example.data.dto.UserDto
import com.example.data.entities.UserEntity

fun UserDto.toUserEntity() =
    UserEntity {
        userId = this@toUserEntity.user_id
        email = this@toUserEntity.user_email
        username = this@toUserEntity.user_name
        userPassword = this@toUserEntity.user_password
        userSalt = this@toUserEntity.user_salt
//      userRegister = this@toUserEntity.userRegister.stringToLocalDateTime()
    }