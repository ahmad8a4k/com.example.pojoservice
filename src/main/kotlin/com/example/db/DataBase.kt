package com.example.db

import com.example.data.tables.UserTable
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

val Database.user
    get() = this.sequenceOf(UserTable)
