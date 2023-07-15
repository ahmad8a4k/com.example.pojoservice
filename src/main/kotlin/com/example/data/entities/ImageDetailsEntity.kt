package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface ImageDetailsEntity : Entity<ImageDetailsEntity> {
    companion object : Entity.Factory<ImageDetailsEntity>()

    var id: Int
    var imgTitle: String
    var url: String
    var categoryId: ImageCategories
    var colorId: Colors
    var categoryDetailsId: CategoryDetails
    var imgDescription: String
    var adminId: AdminEnitiy
    var blurHash: String
    var userAdd: UserEntity
    var watchCount: Int
    var likeCount: Int
    var register: LocalDate
}