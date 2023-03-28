package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDateTime

interface UserEntity : Entity<UserEntity> {
    companion object : Entity.Factory<UserEntity>()
    var userId: Int
    var email : String
    var username: String
    var userPassword: String
    var userSalt: String
    var userRegister: LocalDateTime?
}