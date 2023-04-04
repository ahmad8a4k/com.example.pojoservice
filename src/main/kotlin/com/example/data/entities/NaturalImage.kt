package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface NaturalImage : Entity<NaturalImage> {
    companion object : Entity.Factory<NaturalImage>()

    var id: Int
    var imageTitle: String
    var url: String
    var nCategoryId: NaturalCategories
    var colorId: Colors
    var adminId: Admins
    var register: LocalDate
}