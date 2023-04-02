package com.example.data.tables

import com.example.data.entities.CategoryDetails
import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CategoryDetailsTable : Table<CategoryDetails>("category_details") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val categoryId = int("category_id").references(ImageCategoriesTable) { it.categoryId }
    val details = varchar("details").bindTo { it.details }
    val adminId = int("admin_id").references(AdminsTable) { it.adminId }
    val register = date("register").bindTo { it.register }
}