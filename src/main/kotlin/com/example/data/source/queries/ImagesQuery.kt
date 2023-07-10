package com.example.data.source.queries

import com.example.data.tables.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.expression.ArgumentExpression
import org.ktorm.expression.*
import org.ktorm.schema.ColumnDeclaring
import java.time.LocalDate

fun Database.getTopRatedLiteImagesThreeWeeksAgoQuery(limit: Int, userId: Int):
        Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        )
        .innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        )
        .leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        )
        .selectDistinct(
            ImageDetailsTable.id,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.register,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ColorsTable.colorHex,
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count"),
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .where {
            (ImageDetailsTable.register.greaterEq(LocalDate.now().minusWeeks(3)))
        }
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ImageUserLikesTable.user_id,
            ImageUserLikesTable.image_id
        )
        .orderBy(
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count").desc()
        )
        .limit(n = limit)
}

fun Database.listOfTopRatedLiteImages(pageSize: Int, pageNumber: Int, userId: Int): Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        )
        .innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        )
        .leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        )
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.register,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ColorsTable.colorHex,
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count"),
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ImageUserLikesTable.user_id,
            ImageUserLikesTable.image_id
        )
        .orderBy(
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count").desc()
        )
        .limit(pageSize)
        .offset((pageNumber - 1) * pageSize)
}

fun Database.getLiteImagesOrderByDate(pageSize: Int, page: Int, userId: Int): Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        )
        .innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        )
        .leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        )
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.register,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ColorsTable.colorHex,
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count"),
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ImageUserLikesTable.user_id,
            ImageUserLikesTable.image_id
        ).orderBy(ImageDetailsTable.id.asc())
}

fun Database.getAllLiteImagesByCategoryQuery(
    pageSize: Int,
    page: Int,
    categoryId: Int,
    categoryName: String,
    userId: Int,
): Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        )
        .innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        )
        .leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        )
        .leftJoin(
            right = ImagesTagsTable,
            on = ImageDetailsTable.id.eq(ImagesTagsTable.image_id)
        )
        .leftJoin(
            right = TagsTable,
            on = ImagesTagsTable.tag_id.eq(TagsTable.id)
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
            ).aliased("like_count"),
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .where {
            TagsTable.tag_name.like("%$categoryName%") or
                    ImageCategoriesTable.id.eq(categoryId)
        }
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ImageUserLikesTable.user_id,
            ImageUserLikesTable.image_id
        ).orderBy(ImageDetailsTable.id.desc())
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

fun Database.getImageDetailsByImageIdQuery(imageId: Int, userId: Int): Query {
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
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
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
            ).aliased("watch_count"),
            ImageUserLikesTable.image_id.isNotNull().aliased("image_liked")
        ).limit(1)
        .where {
            ImageDetailsTable.id.eq(imageId)
        }.groupBy(
            ImageDetailsTable.id,
            UserTable.userId,
            ImageUserLikesTable.user_id,
            ImageUserLikesTable.image_id
        )
}

fun Database.getImagesDetailsByColorIdAndCategoryIdQuery(
    categoryID: Int,
    colorId: Int,
    userId: Int,
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
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
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
            ).aliased("watch_count"),
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .where {
            ImageCategoriesTable.id.eq(categoryID) or
                    ColorsTable.id.eq(colorId)
        }
        .groupBy(
            ImageDetailsTable.id,
            UserTable.userId,
            ImageUserLikesTable.user_id,
            ImageUserLikesTable.image_id
        )
        .limit(59)
        .orderBy(
            ImageDetailsTable.id.desc()
        )
}

fun Database.getImagesDetailsBasedOnRandomCategoryIdQuery(limit: Int, userId: Int): Query {
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
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
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
            ).aliased("watch_count"),
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .where {
            ImageCategoriesTable.id.eq(randomCategoryId)
        }
        .limit(limit)
        .orderBy(
            ImageDetailsTable.id.desc()
        )
        .groupBy(
            ImageDetailsTable.id,
            UserTable.userId
        )
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