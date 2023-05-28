package com.example.data.tables

import com.example.data.entities.AdminEnitiy
import org.ktorm.schema.*

object AdminsTable: Table<AdminEnitiy>("admins") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val adminName = varchar("admins_name").bindTo { it.adminName }
    val adminsPassword = varchar("admins_password").bindTo { it.adminPassword }
    val adminsSalt = varchar("admins_salt").bindTo { it.adminSlat }
    val userRegister = date("register").bindTo { it.register }
}