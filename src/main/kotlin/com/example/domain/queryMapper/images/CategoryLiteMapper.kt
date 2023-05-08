package com.example.domain.queryMapper.images

import com.example.data.dto.imageDetails.ImageCategoryLiteDto
import com.example.data.tables.ImageCategoriesTable
import org.ktorm.dsl.QueryRowSet

fun QueryRowSet.toCategoryLiteDto() = ImageCategoryLiteDto(
    category_id = this[ImageCategoriesTable.id] ?: 0,
    category_name = this[ImageCategoriesTable.categoryName] ?: "",
    category_url =  this[ImageCategoriesTable.category_url] ?: "",
)