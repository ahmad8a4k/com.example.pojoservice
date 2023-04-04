package com.example.domain.mapper

import com.example.data.dto.imageDetails.ImageCategoryAdminDto
import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.tables.AdminsTable
import com.example.data.tables.ImageCategoriesTable
import com.example.utils.convertLongToDate
import org.ktorm.dsl.QueryRowSet
import java.sql.Date

fun QueryRowSet.imageCategoryMapper() = ImageCategoryDto(
    category_id = this[ImageCategoriesTable.id] ?: 0,
    category_name = this[ImageCategoriesTable.categoryName] ?: "",
    category_url = this[ImageCategoriesTable.category_url] ?: "",
    admin = ImageCategoryAdminDto(
        admin_id = this[AdminsTable.id] ?: 0,
        admin_name = this[AdminsTable.adminName] ?: "",
    ),
    category_date = Date.valueOf(this[ImageCategoriesTable.categoryDate]).time.convertLongToDate(),
)