package com.example.data.source.queries

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.tables.*
import com.example.domain.queryMapper.images.imageFullDetailsToDto
import com.example.domain.queryMapper.images.liteImageDetailsRow
import com.example.domain.queryMapper.images.liteImageDetailsWithLikesCountRow
import org.ktorm.database.Database
import org.ktorm.dsl.*
import java.time.LocalDate

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
            on = ImageStaImageStatsDetails.imageId eq ImageDetailsTable.id
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

fun Database.liteListImageDetailsQuery(pageSize: Int, page: Int): List<LiteImageDetailsDto> {
    return this.from(ImageDetailsTable)
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy()
        .map { it.liteImageDetailsRow() }
}

fun Database.getTopRatedLiteImagesThisWeekOrLastWeeks():
        List<LiteImageDetailsWithLikesCountDto> {
    val currentDate = LocalDate.now()
    val oneWeekAgo = currentDate.minusWeeks(1)
    val twoWeeksAgo = currentDate.minusWeeks(2)
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = UserSocialTable,
            on = UserSocialTable.image_details_id eq ImageDetailsTable.id
        )
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            count(UserSocialTable.image_details_id).aliased("likes_count")
        )
        .where {
            (ImageDetailsTable.register greaterEq oneWeekAgo) or
                    (ImageDetailsTable.register greaterEq twoWeeksAgo)
        }
        .groupBy(
            ImageDetailsTable.id
        )
        .orderBy(count(UserSocialTable.image_details_id).desc())
        .limit(10)
        .map { it.liteImageDetailsWithLikesCountRow() }
}

fun Database.listOfTopRatedLiteImages(pageSize: Int, pageNumber: Int):
        List<LiteImageDetailsWithLikesCountDto> {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = UserSocialTable,
            on = UserSocialTable.image_details_id eq ImageDetailsTable.id
        )
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            count(UserSocialTable.image_details_id).aliased("likes_count")
        )
        .groupBy(
            ImageDetailsTable.id
        )
        .orderBy(count(UserSocialTable.image_details_id).desc())
        .limit(pageSize)
        .offset((pageNumber - 1) * pageSize)
        .map { it.liteImageDetailsWithLikesCountRow() }
}

fun Database.getAllColorsQuery(): Query {
    return this.from(ColorsTable).select(
        ColorsTable.id,
        ColorsTable.colorName,
        ColorsTable.colorHex
    )
}