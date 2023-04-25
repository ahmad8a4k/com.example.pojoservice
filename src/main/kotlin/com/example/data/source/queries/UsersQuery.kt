package com.example.data.source.queries

import com.example.data.dto.UserDto
import com.example.data.tables.SubscribeTypesTable
import com.example.data.tables.UserTable
import com.example.domain.queryMapper.user.toUserDetailsDto
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.getUserDetailsByUserId(userId: Int): UserDto {
    return this.from(UserTable)
        .innerJoin(
            right = SubscribeTypesTable,
            on = UserTable.userSubscribe eq SubscribeTypesTable.id
        )
        .select()
        .where(UserTable.userId eq userId)
        .map { it.toUserDetailsDto() }
        .first()
}

fun Database.checkIfUserExistByUsingUserId(userId: Int): Boolean {
    val result = this.from(UserTable)
        .select(UserTable.userId)
        .where { UserTable.userId eq userId }.map { (it[UserTable.userId] ?: 0) == userId }
    return result.isNotEmpty() && result[0]
}