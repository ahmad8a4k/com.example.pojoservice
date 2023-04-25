package com.example.data.tables

import com.example.data.entities.NaturalCategories
import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object NaturalCategoryTable : Table<NaturalCategories>("natural_category") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val category_name = varchar("category_name").bindTo { it.name }
    val category_url = varchar("category_url").bindTo { it.categoryUrl }
    val register = date("category_date").bindTo { it.register }
}