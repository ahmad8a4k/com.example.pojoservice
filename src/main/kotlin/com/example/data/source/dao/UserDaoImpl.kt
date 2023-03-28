package com.example.data.source.dao

import com.example.data.dto.UserDto
import com.example.data.mapper.toUserEntity
import com.example.data.tables.UserTable
import com.example.db.user
import com.example.domain.mapper.toUserDto
import com.example.utils.checkIfExistByName
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.update

class UserDaoImpl constructor(
    private var dataBase: Database,
) : UserDao {

    override suspend fun checkIfUserExistByName(username: String): Boolean {
        return dataBase.checkIfExistByName(UserTable.userName, username)
    }

    override suspend fun getUserByUserName(userName: String): UserDto? {
        return dataBase.user.find { UserTable.userName eq userName }?.toUserDto()
    }

    // ? this not here
    override suspend fun insertUser(user: UserDto): Boolean {
        return dataBase.user.add(user.toUserEntity()) == 1
    }

    override suspend fun updateUserPassword(user: UserDto): Boolean {
        return dataBase.user.update(user.toUserEntity()) == 1
    }

    // DELETE FROM `pharmacy_manage`.`user` WHERE (`id` = '00');
    override suspend fun deleteUser(username: String): Boolean {
        return dataBase.user.find { userTable -> userTable.userName eq username }?.delete() == 1
    }

}