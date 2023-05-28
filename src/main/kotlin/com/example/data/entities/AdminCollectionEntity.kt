package com.example.data.entities

import org.ktorm.entity.Entity

interface AdminCollectionEntity : Entity<AdminCollectionEntity> {
    companion object : Entity.Factory<AdminCollectionEntity>()

    var aId: Int
    var aAdminId: AdminEnitiy
    var aCollectionName: String
    var aCollectionDescription: String
    var aCollectionInvisibility: Boolean
    var aLikesCount: Int
    var collectionUrl : String
}