package com.example.data.source.queries

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.tables.*
import com.example.domain.queryMapper.images.liteImageDetailsResultSet
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
            ImageDetailsTable.watchCount,
            ColorsTable.id,
            ColorsTable.colorHex,
            ImageDetailsTable.likeCount,
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .where {
            ImageDetailsTable.register.greaterEq(LocalDate.now().minusWeeks(4))
        }
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ImageUserLikesTable.user_id,
            ImageUserLikesTable.image_id
        )
        .limit(n = limit)
        .orderBy(
            ImageDetailsTable.likeCount.desc()
        )
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
            ImageDetailsTable.watchCount,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ColorsTable.colorHex,
            ImageDetailsTable.likeCount,
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
            ImageDetailsTable.likeCount.desc()
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
            ImageDetailsTable.watchCount,
            ImageDetailsTable.likeCount,
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ImageUserLikesTable.image_id
        ).orderBy(ImageDetailsTable.register.desc())
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
            ImageDetailsTable.watchCount,
            ColorsTable.id,
            ColorsTable.colorHex,
            ImageDetailsTable.likeCount,
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
            ImageDetailsTable.likeCount,
            ImageDetailsTable.watchCount,
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
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        ).innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        ).innerJoin(
            UserTable,
            on = ImageDetailsTable.userAdd.eq(UserTable.userId)
        ).leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        ).selectDistinct(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.imgDescription,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            ImageDetailsTable.likeCount,
            ImageDetailsTable.watchCount,
            ImageDetailsTable.register,
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
        ).orderBy(
            ImageDetailsTable.register.desc(),
            ImageDetailsTable.id.desc()
        )
        .limit(59)
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
        ).selectDistinct(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.imgDescription,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            ImageDetailsTable.likeCount,
            ImageDetailsTable.watchCount,
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
            UserTable.userId,
            ImageUserLikesTable.image_id
        )
}

fun Database.searchImagesByImageTitleName(userId: Int, title: String): List<LiteImageDetailsDto> {
    val qResult = mutableListOf<LiteImageDetailsDto>()
    this.useConnection { conn ->
        val queryResult = conn.prepareStatement(
            """
            SELECT 
                image_details.id, 
                image_details.img_title,
                image_details.url, 
                image_details.blur_hash,
                image_details.register,
                image_details.watch_count,
                image_details.like_count,
                img_categories.id,
                colors.id,
                colors.color_hex,
                CASE WHEN images_user_likes.image_id IS NULL THEN false ELSE true END AS user_liked
            FROM 
                image_details
            INNER JOIN 
                img_categories ON image_details.category_id = img_categories.id
            INNER JOIN 
                colors ON image_details.color_id = colors.id
            LEFT JOIN 
                images_user_likes ON image_details.id = images_user_likes.image_id AND 
                images_user_likes.user_id = $userId
            WHERE 
                to_tsvector('english', img_title) @@ to_tsquery('english', '$title')
            ORDER BY image_details.register DESC
            LIMIT 200
        """
        ).executeQuery()
        while (queryResult.next()) {
            qResult.add(queryResult.liteImageDetailsResultSet())
        }
        return qResult.toList()
    }
}

fun Database.getImageSearchResultByTagName(userId: Int, tagName: String): Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        ).innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        ).innerJoin(
            right = UserTable,
            on = ImageDetailsTable.userAdd.eq(UserTable.userId)
        ).innerJoin(
            right = ImagesTagsTable,
            on = ImageDetailsTable.id.eq(ImagesTagsTable.image_id)
        ).innerJoin(
            right = TagsTable,
            on = TagsTable.id.eq(ImagesTagsTable.tag_id)
        ).leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        ).selectDistinct(
            ImageDetailsTable.id,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.url,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.imgDescription,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            ImageDetailsTable.likeCount,
            ImageDetailsTable.watchCount,
            ImageDetailsTable.register,
            ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")
        ).where {
            TagsTable.tag_name.like("%$tagName%")
        }.orderBy(
            ImageDetailsTable.register.desc()
        ).limit(
            200
        )
}

fun Database.removeUserLikeImage(userId: Int, imageId: Int): Int {
    return this.delete(ImageUserLikesTable) {
        it.user_id.eq(userId) and it.image_id.eq(imageId)
    }
}

fun Database.addUserLikeImage(userId: Int, imageId: Int): Int {
    return this.insert(ImageUserLikesTable) {
        this.set(ImageUserLikesTable.user_id, userId)
        this.set(ImageUserLikesTable.image_id, imageId)
    }
}

fun Database.checkIfUserLikedImage(userId: Int, imageId: Int): Query {
    return this.from(ImageUserLikesTable)
        .select(
            coalesce(
                count(
                    ImageUserLikesTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count")
        ).where {
            ImageUserLikesTable.user_id.eq(userId) and
                    ImageUserLikesTable.image_id.eq(imageId)
        }
}

fun Database.updateWatchImage(imageId: Int): Int {
    return this.update(ImageDetailsTable) {
        set(ImageDetailsTable.watchCount, ImageDetailsTable.watchCount + 1)
        this.where { ImageDetailsTable.id.eq(imageId) }
    }
}

fun Database.updateLikedImageCountByIncrease(imageId: Int): Int {
    return this.update(ImageDetailsTable) {
        set(ImageDetailsTable.likeCount, ImageDetailsTable.likeCount + 1)
        this.where { ImageDetailsTable.id.eq(imageId) }
    }
}

fun Database.updateLikedImageCountByDecrease(imageId: Int): Int {
    return this.update(ImageDetailsTable) {
        set(ImageDetailsTable.likeCount, ImageDetailsTable.likeCount - 1)
        this.where { ImageDetailsTable.id.eq(imageId) }
    }
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