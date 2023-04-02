package com.example.routes.mapper.user.signupMapper

import com.example.data.dto.UserDto
import com.example.data.request.UserRegisterRequest
import com.example.utils.generateSaltedHash

fun UserRegisterRequest.userRequestToDto(): UserDto {
    val saltedHash = password.generateSaltedHash()
    return UserDto(
        user_id = 0,
        user_name = username,
        user_password = saltedHash.hash,
        user_email = userEmail ,
        user_salt = saltedHash.salt
    )
}