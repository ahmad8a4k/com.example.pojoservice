package com.example.data.tables

import com.example.data.entities.ImageCategories

import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object ImageCategoriesTable : Table<ImageCategories>("img_categories") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val categoryName = varchar("category_name").bindTo { it.categoryName }
    val adminAdded = int("admin_added").references(AdminsTable) { it.adminAdded }
    val categoryDate = date("category_date").bindTo { it.categoryDate }
}