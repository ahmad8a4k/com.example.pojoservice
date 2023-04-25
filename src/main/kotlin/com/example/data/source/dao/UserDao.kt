package com.example.data.source.dao

import com.example.data.dto.UserDto

interface UserDao {

    suspend fun insertUser(user: UserDto): UserDto

    suspend fun updateUserPassword(user: UserDto): Boolean

    suspend fun deleteUser(username: String): Boolean

    suspend fun checkIfUserExistByName(username: String): Boolean

    suspend fun getUserByUserName(userName: String): UserDto
    suspend fun getUserInformationByUserId(userId: Int): UserDto

    suspend fun checkIfUserExistOrNotByUserId(userId: Int): Boolean

}