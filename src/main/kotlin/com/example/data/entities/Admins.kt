package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate


interface Admins  : Entity<Admins> {
    companion object : Entity.Factory<Admins>()
    var id: Int
    var adminName:String
    var adminPassword:String
    var adminSlat:String
    var register: LocalDate
}