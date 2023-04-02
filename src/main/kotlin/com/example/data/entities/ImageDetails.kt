package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface ImageDetails : Entity<ImageDetails> {
    companion object : Entity.Factory<ImageDetails>()
    var id:Int
    var imgTitle:String
    var url:String
    var categoryId:ImageCategories
    var colorId:Colors
    var categoryDetailsId:CategoryDetails
    var imgDescription:String
    var adminId:Admins
    var register:LocalDate
}