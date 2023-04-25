package com.example.data.entities

import org.ktorm.entity.Entity

interface SocialDetailsEntity : Entity<SocialDetailsEntity> {
    companion object : Entity.Factory<SocialDetailsEntity>()
    var id :Int
    var socialName:String
}