package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate


interface AdminEnitiy  : Entity<AdminEnitiy> {
    companion object : Entity.Factory<AdminEnitiy>()
    var id: Int
    var adminName:String
    var adminPassword:String
    var adminSlat:String
    var register: LocalDate
}