package com.example.utils

import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.tables.*
import com.example.domain.mapper.imageFullDetailsToDto
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import kotlin.math.ceil


fun <T : Entity<T>> Database.getCountOfTableItemsQuery(table: Table<T>): Int {
    return this.from(table).select().map { }.count()
}

fun <T : Entity<T>> Database.getTotalPagesTableQuery(table: Table<T>, pageSize: Int): Int {
    val itemCount = this.from(table).select().map {}.count()
    return ceil(itemCount.toDouble() / pageSize.toDouble()).toInt()
}


fun Database.imageFullDetailsQuery(pageSize: Int, page: Int): List<ImageDetailsFullDto> {
    return this.from(ImageDetailsTable)
        .innerJoin(
            ColorsTable,
            on = ImageDetailsTable.colorId eq ColorsTable.id
        )
        .innerJoin(
            ImageCategoriesTable,
            on = ImageDetailsTable.categoryId eq ImageCategoriesTable.id
        )
        .innerJoin(
            CategoryDetailsTable,
            CategoryDetailsTable.id eq ImageDetailsTable.categoryDetailsId
        )
        .innerJoin(
            ImageStaImageStatsDetails,
            on = ImageDetailsTable.id eq ImageStaImageStatsDetails.imageId
        )
        .innerJoin(
            AdminsTable,
            on = AdminsTable.id eq ImageDetailsTable.adminId
        )
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            ImageDetailsTable.register,
            ColorsTable.id,
            ColorsTable.colorName,
            ImageCategoriesTable.id,
            ImageCategoriesTable.categoryName,
            CategoryDetailsTable.id,
            CategoryDetailsTable.details,
            AdminsTable.id,
            AdminsTable.adminName,
            ImageStaImageStatsDetails.id,
            ImageStaImageStatsDetails.likesNum,
            ImageStaImageStatsDetails.watchNum,
            ImageStaImageStatsDetails.downloadNum,
            ImageStaImageStatsDetails.saveNum,
            ImageStaImageStatsDetails.shareNum,
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .map { it.imageFullDetailsToDto() }
}