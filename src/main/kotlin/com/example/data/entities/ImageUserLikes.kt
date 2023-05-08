package com.example.data.entities

import org.ktorm.entity.Entity

interface ImageUserLikes: Entity<ImageUserLikes> {
    companion object : Entity.Factory<ImageUserLikes>()

    var userId:UserEntity
    var imageId:ImageDetails
}