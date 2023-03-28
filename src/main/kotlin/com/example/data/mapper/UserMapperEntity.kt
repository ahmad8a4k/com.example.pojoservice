package com.example.data.mapper

import com.example.data.dto.UserDto
import com.example.data.entities.UserEntity

fun UserDto.toUserEntity() =
    UserEntity {
        userId = this@toUserEntity.userId
        email = this@toUserEntity.email
        username = this@toUserEntity.userName
        userPassword = this@toUserEntity.userPassword
        userSalt = this@toUserEntity.userSalt
//      userRegister = this@toUserEntity.userRegister.stringToLocalDateTime()
    }