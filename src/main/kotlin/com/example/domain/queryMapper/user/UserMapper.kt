package com.example.domain.queryMapper.user

import com.example.data.dto.SubscribeTypeDto
import com.example.data.dto.UserDto
import com.example.data.tables.SubscribeTypesTable
import com.example.data.tables.UserTable
import org.ktorm.dsl.QueryRowSet
import org.ktorm.support.postgresql.DefaultValueExpression

fun QueryRowSet.toUserDetailsDto() = UserDto(
    user_id = this[UserTable.userId] ?:0,
    user_name = this[UserTable.userName] ?: "0",
    full_name = this[UserTable.fullName] ?: "0",
    user_email = this[UserTable.email] ?: "0",
    subscribe_details = SubscribeTypeDto(
        subscrib_id = this[SubscribeTypesTable.id] ?: 0,
        subscribe_type_name = this[SubscribeTypesTable.subscribe_name] ?: "Empty"
    ),
    user_password = this[UserTable.userPassword] ?: "0",
    user_salt = this[UserTable.userSalt] ?: "0",
)