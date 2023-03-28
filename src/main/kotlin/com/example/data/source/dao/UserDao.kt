package com.example.data.source.dao

import com.example.data.dto.UserDto

interface UserDao {

    suspend fun insertUser(user: UserDto): Boolean

    suspend fun updateUserPassword(user: UserDto): Boolean

    suspend fun deleteUser(username: String): Boolean

    suspend fun checkIfUserExistByName(username: String): Boolean

    suspend fun getUserByUserName(userName: String): UserDto?

}