package com.example.data.entities

import org.ktorm.entity.Entity

interface NaturalImagesTag : Entity<NaturalImagesTag> {
    companion object : Entity.Factory<NaturalImagesTag>()
    var naturalTag: NaturalTag
    var naturalImages: NaturalImage
}