package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface CategoryDetails : Entity<CategoryDetails> {
    companion object : Entity.Factory<CategoryDetails>()
    var id:Int
    var categoryId: ImageCategories
    var details:String
    var adminId:Admins
    var register: LocalDate
}