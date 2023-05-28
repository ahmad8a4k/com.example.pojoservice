package com.example.data.entities

import org.ktorm.entity.Entity

interface ImageUsersWatchEntity : Entity<ImageUsersWatchEntity> {
    companion object : Entity.Factory<ImageUsersWatchEntity>()

    var userId:UserEntity
    var imageId:ImageDetailsEntity
}