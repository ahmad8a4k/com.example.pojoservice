package com.example.data.source.queries

import com.example.data.tables.ColorsTable
import com.example.data.tables.ImageCategoriesTable
import org.ktorm.database.Database
import org.ktorm.dsl.*


fun Database.getAllColorsQuery(): Query {
    return this.from(ColorsTable).select(
        ColorsTable.id,
        ColorsTable.colorName,
        ColorsTable.colorHex
    )
}

fun Database.getAllImagesCategoriesLite(): Query {
    return this.from(ImageCategoriesTable).select(
        ImageCategoriesTable.id,
        ImageCategoriesTable.categoryName,
        ImageCategoriesTable.category_url
    ).orderBy(ImageCategoriesTable.id.asc())
}