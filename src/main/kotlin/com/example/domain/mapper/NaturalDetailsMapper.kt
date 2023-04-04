package com.example.domain.mapper

import com.example.data.dto.imageDetails.*
import com.example.data.tables.*
import com.example.utils.convertLongToDate
import org.ktorm.dsl.QueryRowSet
import java.sql.Date

fun QueryRowSet.naturalFullDetailsToDto() = NaturalDetailsDto(
    natural_id = this[NaturalTable.id] ?: 0,
    image_Title = this[NaturalTable.image_title] ?: "Empty",
    natural_url = this[NaturalTable.url] ?: "Empty",
    natural_category = NaturalCategoriesDto(
        category_id = this[NaturalCategoryTable.id] ?: 0,
        natural_category_name = this[NaturalCategoryTable.category_name] ?: "Empty",
        natural_category_url = this[NaturalCategoryTable.category_url] ?: "Empty",
        natural_category_register = Date.valueOf(this[NaturalCategoryTable.register]).time.convertLongToDate()
    ),
    color = ImageDetailsColorDto(
        color_id = this[ColorsTable.id] ?: 0,
        color_name = this[ColorsTable.colorName] ?: "Empty"
    ),
    admin = ImageDetailsAdminDto(
        admin_id = this[AdminsTable.id] ?: 0,
        admin_name = this[AdminsTable.adminName] ?: "Empty"
    ),
    register = Date.valueOf(this[NaturalTable.register]).time.convertLongToDate()
)

fun QueryRowSet.naturalCategoryDto() = NaturalCategoriesDto(
    category_id = this[NaturalCategoryTable.id] ?: 0,
    natural_category_name = this[NaturalCategoryTable.category_name] ?: "Empty",
    natural_category_url = this[NaturalCategoryTable.category_url] ?: "Empty"
)
