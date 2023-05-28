package com.example.data.entities

import org.ktorm.entity.Entity

interface ImageNumDetails : Entity<ImageNumDetails> {
    companion object : Entity.Factory<ImageNumDetails>()

    var id: Int
    var imageId: ImageDetailsEntity
    var likesNum: Int
    var sharNum: Int
    var downloadNum: Int
    var watchNum: Int
    var saveNum: Int
}