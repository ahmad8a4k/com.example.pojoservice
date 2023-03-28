package com.example.data.repositories.userRepository

import com.example.data.dto.UserDto
import com.example.data.source.dao.UserDao

class UserRepositoryImpl constructor(
    private var userDao: UserDao,
) : UserRepository {

    override suspend fun getUserByUserName(username: String): UserDto? {
        return userDao.getUserByUserName(userName = username)
    }

    override suspend fun updateUserPassword(user: UserDto): Boolean {
        return userDao.updateUserPassword(user)
    }

    override suspend fun checkIfUserExistByName(username: String): Boolean {
        return userDao.checkIfUserExistByName(username)
    }

    override suspend fun insertUser(user: UserDto): Boolean {
        return userDao.insertUser(user = user)
    }

//    override suspend fun updateUserPassword(user: UserDto): Boolean {
//        val updateUserPassword = dataBase.update(UserTable) { row ->
//            set(row.userPassword, user.userPassword)
//            set(row.userSalt, user.userSalt)
//            where { row.userId eq user.userId }
//        }
//        return updateUserPassword == 1
//    }

    override suspend fun deleteUser(username: String): Boolean {
        return userDao.deleteUser(username)
    }
}