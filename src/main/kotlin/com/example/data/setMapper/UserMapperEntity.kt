package com.example.data.setMapper

import com.example.data.dto.UserDto
import com.example.data.tables.SubscribeTypesTable
import com.example.data.tables.TagsTable.primaryKey
import com.example.data.tables.TagsTable.primaryKeys
import com.example.data.tables.UserTable
import io.ktor.server.sessions.*
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.support.postgresql.defaultValue

fun AssignmentsBuilder.toUserEntityByBuilder(user:UserDto){
    this.set(UserTable.userId, UserTable.userId.defaultValue())
    this.set(UserTable.userName, user.user_name)
    this.set(UserTable.fullName, user.full_name)
    this.set(UserTable.email, user.user_email)
    this.set(UserTable.userPassword, user.user_password)
    this.set(UserTable.userSubscribe, user.subscribe_details.subscrib_id)
    this.set(UserTable.userSalt, user.user_salt)
}

fun AssignmentsBuilder.toUserEntityUpdatePasswordByBuilder(user:UserDto){
    this.set(UserTable.userPassword, user.user_password)
    this.set(UserTable.userSalt, user.user_salt)
}