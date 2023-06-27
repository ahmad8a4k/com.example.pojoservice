package com.example.data.source.dao

import com.example.data.dto.UserDto
import com.example.data.setMapper.toUserEntityByBuilder
import com.example.data.setMapper.toUserEntityUpdatePasswordByBuilder
import com.example.data.source.queries.checkIfExistByNameAndTableName
import com.example.data.source.queries.checkIfUserExistByUsingUserId
import com.example.data.source.queries.getUserDetailsByUserId
import com.example.data.tables.UserTable
import com.example.domain.queryMapper.user.toUserDetailsDto
import org.ktorm.database.Database
import org.ktorm.dsl.*

class UserDaoImpl(
    private var dataBase: Database,
) : UserDao {

    override suspend fun checkIfUserNameExist(username: String): Boolean {
        return dataBase.checkIfExistByNameAndTableName(UserTable.userName, username)
    }

    override suspend fun checkIfUserEmailExist(email: String): Boolean {
        return dataBase.checkIfExistByNameAndTableName(UserTable.email, email)
    }

    override suspend fun getUserByUserName(userName: String): UserDto {
        return dataBase.from(UserTable).select()
            .where { UserTable.userName eq userName }.map {
                it.toUserDetailsDto()
            }.first()
    }

    override suspend fun getUserByUserEmail(email: String): UserDto {
        return dataBase.from(UserTable).select()
            .where { UserTable.email eq email }.map {
                it.toUserDetailsDto()
            }.first()
    }

    override suspend fun insertUser(user: UserDto): UserDto {
        dataBase.insert(UserTable) { _ ->
            this.toUserEntityByBuilder(user)
        }
        return dataBase.from(UserTable).select()
            .where { UserTable.userName eq user.user_name }.map {
                it.toUserDetailsDto()
            }.first()
    }

    override suspend fun updateUserPassword(user: UserDto): Boolean {
        return dataBase.update(UserTable) {
            this.toUserEntityUpdatePasswordByBuilder(user)
            where { UserTable.userId eq user.user_id }
        } == 1
    }

    // DELETE FROM `pharmacy_manage`.`user` WHERE (`id` = '00');
    override suspend fun deleteUser(username: String): Boolean {
        return dataBase.delete(UserTable) {
            it.userName eq username
        } == 1
    }

    override suspend fun getUserInformationByUserId(userId: Int): UserDto {
        return dataBase.getUserDetailsByUserId(userId = userId)
    }

    override suspend fun checkIfUserExistOrNotByUserId(userId: Int): Boolean {
        return dataBase.checkIfUserExistByUsingUserId(userId)
    }
}