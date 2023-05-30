package com.example.data.entities

import org.ktorm.entity.Entity

interface UserAdminCollectionLikeEntity : Entity<UserAdminCollectionLikeEntity> {
    companion object : Entity.Factory<UserAdminCollectionLikeEntity>()

    var userId: UserEntity
    var collectionId: AdminCollectionEntity
}