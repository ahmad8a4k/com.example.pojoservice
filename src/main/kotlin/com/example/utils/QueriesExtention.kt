package com.example.utils

import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.dto.imageDetails.NaturalDetailsDto
import com.example.data.tables.*
import com.example.domain.mapper.imageFullDetailsToDto
import com.example.domain.mapper.naturalFullDetailsToDto
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
        .leftJoin(
            ColorsTable,
            on = ImageDetailsTable.colorId eq ColorsTable.id
        )
        .leftJoin(
            ImageCategoriesTable,
            on = ImageDetailsTable.categoryId eq ImageCategoriesTable.id
        )
        .leftJoin(
            CategoryDetailsTable,
            CategoryDetailsTable.id eq ImageDetailsTable.categoryDetailsId
        )
        .leftJoin(
            ImageStaImageStatsDetails,
            on = ImageDetailsTable.id eq ImageStaImageStatsDetails.imageId
        )
        .leftJoin(
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
            ImageCategoriesTable.category_url,
            CategoryDetailsTable.id,
            CategoryDetailsTable.details,
            ImageDetailsTable.imgDescription,
            AdminsTable.id,
            AdminsTable.adminName,
            ImageStaImageStatsDetails.id,
            ImageStaImageStatsDetails.likesNum,
            ImageStaImageStatsDetails.watchNum,
            ImageStaImageStatsDetails.downloadNum,
            ImageStaImageStatsDetails.saveNum,
            ImageStaImageStatsDetails.shareNum
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy()
        .map { it.imageFullDetailsToDto() }
}

fun Database.naturalDetailsQuery(pageSize: Int, page: Int): List<NaturalDetailsDto> {
    return this.from(NaturalTable)
        .leftJoin(
            NaturalCategoryTable,
            NaturalCategoryTable.id eq NaturalTable.n_category_id
        )
        .leftJoin(
            ColorsTable,
            on = NaturalTable.color_id eq ColorsTable.id
        )
        .leftJoin(
            AdminsTable,
            on = AdminsTable.id eq NaturalTable.admin_id
        )
        .select(
            NaturalTable.id,
            NaturalTable.image_title,
            NaturalTable.url,
            NaturalTable.register,
            ColorsTable.id,
            ColorsTable.colorName,
            NaturalCategoryTable.id,
            NaturalCategoryTable.category_name,
            NaturalCategoryTable.category_url,
            NaturalCategoryTable.register,
            AdminsTable.id,
            AdminsTable.adminName,
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy()
        .map { it.naturalFullDetailsToDto() }
}