package com.example.data.entities

import org.ktorm.entity.Entity

interface NaturalTag : Entity<NaturalTag> {
    companion object : Entity.Factory<NaturalTag>()
    var id:Int
    var tagName:String
}