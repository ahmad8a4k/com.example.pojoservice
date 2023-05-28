package com.example.data.entities

import org.ktorm.entity.Entity

interface AdminImageCollectionEntity  : Entity<AdminImageCollectionEntity> {
    companion object : Entity.Factory<AdminImageCollectionEntity>()

    var adminCollectionId: AdminCollectionEntity
    var aImageDetailsEntity : ImageDetailsEntity
}