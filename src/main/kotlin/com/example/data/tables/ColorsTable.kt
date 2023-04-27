package com.example.data.tables

import com.example.data.entities.Colors
import org.ktorm.schema.*

object ColorsTable : Table<Colors>("colors") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val colorName = varchar("color_name").bindTo { it.colorName }
    val adminId = int("admin_added").references(AdminsTable) { it.adminAdded }
    val colorDate = date("color_date").bindTo { it.colorDate }
    val colorHex = varchar("color_hex").bindTo { it.colorHex }
}