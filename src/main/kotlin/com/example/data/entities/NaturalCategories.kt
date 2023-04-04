package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface NaturalCategories : Entity<NaturalCategories> {
    companion object : Entity.Factory<NaturalCategories>()

    var id: Int
    var name: String
    var categoryUrl: String
    var register: LocalDate
}