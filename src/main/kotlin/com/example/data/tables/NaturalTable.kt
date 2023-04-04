package com.example.data.tables

import com.example.data.entities.NaturalImage
import org.ktorm.schema.*

object NaturalTable : Table<NaturalImage>("natural_images") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val image_title = varchar("image_title").bindTo { it.imageTitle }
    val url = varchar("url").bindTo { it.url }
    val n_category_id = int("n_category_id").references(NaturalCategoryTable) { it.nCategoryId }
    val color_id = int("color_id").references(ColorsTable) { it.colorId }
    val admin_id = int("admin_id").references(AdminsTable) { it.adminId }
    val register = date("register").bindTo { it.register }
}