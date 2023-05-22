package com.example.data.tables

import com.example.data.entities.UserEntity
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserTable : Table<UserEntity>("pojo_user") {
    val userId = int("id").bindTo { it.userId }.primaryKey()
    val email = varchar("email").bindTo { it.email }
    val userName = varchar("username").bindTo { it.username }
    val fullName = varchar("full_name").bindTo { it.fullName }
    val userPassword = varchar("userpassword").bindTo { it.userPassword }
    val userSalt = varchar("salt").bindTo { it.userSalt }
    val userSubscribe = int("subscribe_id").references(SubscribeTypesTable) { it.userSubscribe }
    val userRegister = datetime("register").bindTo { it.userRegister }
    val userUrl = varchar("user_url").bindTo { it.userUrl }
}