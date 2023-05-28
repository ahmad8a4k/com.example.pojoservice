package com.example.data.entities

import org.ktorm.entity.Entity

interface ImageTags : Entity<ImageTags> {
    companion object : Entity.Factory<ImageTags>()
    var imageId: ImageDetailsEntity
    var tagId: Tags
}