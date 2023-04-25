package com.example.domain.queryMapper.natural

import com.example.data.dto.imageDetails.NaturalCategoriesDto
import com.example.data.tables.NaturalCategoryTable
import org.ktorm.dsl.QueryRowSet

fun QueryRowSet.naturalCategoryDto() = NaturalCategoriesDto(
    category_id = this[NaturalCategoryTable.id] ?: 0,
    natural_category_name = this[NaturalCategoryTable.category_name] ?: "Empty",
    natural_category_url = this[NaturalCategoryTable.category_url] ?: "Empty"
)