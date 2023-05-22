package com.example.data.tables

import com.example.data.entities.ImageDetails
import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object ImageDetailsTable : Table<ImageDetails>("image_details") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val imgTitle = varchar("img_title").bindTo { it.imgTitle }
    val url = varchar("url").bindTo { it.url }
    val categoryId = int("category_id").references(ImageCategoriesTable) { it.categoryId }
    val colorId = int("color_id").references(ColorsTable) { it.colorId }
    val categoryDetailsId = int("category_details_id").references(CategoryDetailsTable) { it.categoryDetailsId }
    val imgDescription = varchar("img_description").bindTo { it.imgDescription }
    val adminId = int("admin_id").references(AdminsTable) { it.adminId }
    val blur_hash = varchar("blur_hash").bindTo { it.blurHash }
    val register = date("register").bindTo { it.register }
    val userAdd = int("user_add_id").references(UserTable) { it.userAdd }
}