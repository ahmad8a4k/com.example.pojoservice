package com.example.data.source.queries

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.LiteImageDetailsWithLikesCountAndTitleDto
import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.tables.*
import com.example.domain.queryMapper.images.imageFullDetailsToDto
import com.example.domain.queryMapper.images.liteImageDetailsRow
import com.example.domain.queryMapper.images.liteImageDetailsWithLikesCountRow
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.expression.ArgumentExpression
import org.ktorm.expression.*
import org.ktorm.schema.ColumnDeclaring
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
        List<LiteImageDetailsWithLikesCountAndTitleDto> {
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
        List<LiteImageDetailsWithLikesCountAndTitleDto> {
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

fun Database.getAllLiteImagesByCategoryQuery(
    pageSize: Int,
    page: Int,
    categoryId: Int,
    categoryName: String,
): Query {
    return this.from(ImageDetailsTable)
        .leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id)
        )
        .leftJoin(
            right = ImagesTagsTable,
            on = ImageDetailsTable.id.eq(ImagesTagsTable.image_id)
        )
        .leftJoin(
            right = TagsTable,
            on = ImagesTagsTable.tag_id.eq(TagsTable.id)
        )
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        )
        .innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        )
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ColorsTable.colorHex,
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count")
        )
        .where {
            TagsTable.tag_name.like("%$categoryName%") or
                    ImageCategoriesTable.id.eq(categoryId)
        }
        .orderBy(ImageDetailsTable.id.desc())
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id
        ).limit(pageSize)
        .offset((page - 1) * pageSize)
}

fun Database.getIdAnUrlFromAllLiteImages(): Query {
    return this.from(ImageDetailsTable)
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash
        )
        .orderBy(ImageDetailsTable.id.desc())
}

fun Database.getImageDetailsByImageIdQuery(imageId: Int): Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            ImageCategoriesTable,
            on = ImageDetailsTable.categoryId eq ImageCategoriesTable.id
        ).innerJoin(
            ColorsTable,
            on = ImageDetailsTable.colorId eq ColorsTable.id
        ).innerJoin(
            UserTable,
            on = ImageDetailsTable.userAdd.eq(UserTable.userId)
        ).leftJoin(
            right = ImageUsersWatchTable,
            on = ImageDetailsTable.id.eq(ImageUsersWatchTable.image_id)
        ).leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id)
        ).select(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.imgDescription,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count"),
            coalesce(
                count(
                    ImageUsersWatchTable.user_id
                ),
                defaultValue = 0
            ).aliased("watch_count")
        ).where {
            ImageDetailsTable.id.eq(imageId)
        }.groupBy(
            ImageDetailsTable.id,
            UserTable.userId
        ).limit(1)
}

fun Database.getImagesDetailsByColorIdAndCategoryIdQuery(
    categoryID: Int,
    colorId: Int,
): Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        ).innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        ).innerJoin(
            UserTable,
            on = ImageDetailsTable.userAdd.eq(UserTable.userId)
        ).leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id)
        ).leftJoin(
            right = ImageUsersWatchTable,
            on = ImageDetailsTable.id.eq(ImageUsersWatchTable.image_id)
        )
        .selectDistinct(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.imgDescription,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count"),
            coalesce(
                count(
                    ImageUsersWatchTable.user_id
                ),
                defaultValue = 0
            ).aliased("watch_count")
        )
        .where {
            ImageCategoriesTable.id.eq(categoryID) or
                    ColorsTable.id.eq(colorId)
        }
        .orderBy(
            ImageDetailsTable.id.desc()
        )
        .groupBy(
            ImageDetailsTable.id,
            UserTable.userId
        ).limit(59)
}

fun Database.getImagesDetailsBasedOnRandomCategoryIdQuery(limit: Int): Query {
    val randomCategoryId = (1..10).shuffled().random()
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        ).innerJoin(
            UserTable,
            on = ImageDetailsTable.userAdd.eq(UserTable.userId)
        ).leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id)
        ).leftJoin(
            right = ImageUsersWatchTable,
            on = ImageDetailsTable.id.eq(ImageUsersWatchTable.image_id)
        )
        .selectDistinct(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.imgDescription,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count"),
            coalesce(
                count(
                    ImageUsersWatchTable.user_id
                ),
                defaultValue = 0
            ).aliased("watch_count")
        )
        .where {
            ImageCategoriesTable.id.eq(randomCategoryId)
        }
        .orderBy(
            ImageDetailsTable.id.desc()
        )
        .groupBy(
            ImageDetailsTable.id,
            UserTable.userId
        ).limit(limit)
}

/**
 *  If Their No Value Set Default Value
 */

fun <T : Any> coalesce(column: ColumnDeclaring<T>, defaultValue: T): FunctionExpression<T> {
    return FunctionExpression(
        functionName = "coalesce",
        arguments = listOf(column.asExpression(), ArgumentExpression(defaultValue, column.sqlType)),
        sqlType = column.sqlType
    )
}

/**
 * For Admin In Future
 */
fun Database.updateImageBlurHashByImageId(imageId: Int, blurHash: String) {
    this.update(ImageDetailsTable) { imageTable ->
        set(imageTable.blur_hash, blurHash)
        where {
            imageTable.id eq imageId
        }
    }
}