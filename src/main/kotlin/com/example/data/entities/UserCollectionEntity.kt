package com.example.data.entities

import org.ktorm.entity.Entity

interface UserCollectionEntity : Entity<UserCollectionEntity> {
    companion object : Entity.Factory<UserCollectionEntity>()

    var id: Int
    var userId: UserEntity
    var collectionName: String
    var collectionDescription: String
    var collectionInvisibility: Boolean
    var likesCount: Int
    var collectionUrl : String
}