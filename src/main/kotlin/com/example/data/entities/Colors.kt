package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface Colors: Entity<Colors> {
    companion object : Entity.Factory<Colors>()
    var id:Int
    var colorName:String
    var adminAdded:Admins
    var colorDate: LocalDate
    var colorHex:String
}