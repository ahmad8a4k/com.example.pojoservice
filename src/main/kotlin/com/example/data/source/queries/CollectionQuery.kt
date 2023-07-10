package com.example.data.source.queries

import com.example.data.tables.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.getUsersCollections(): Query {
    return this.from(UserCollectionTable)
        .innerJoin(
            right = UserTable,
            on = UserTable.userId.eq(UserCollectionTable.userId)
        )
        .leftJoin(
            right = UserCollectionsLikeTable,
            on = UserCollectionTable.id.eq(UserCollectionsLikeTable.collection_id)
        )
        .select(
            UserCollectionTable.id,
            UserCollectionTable.collectionName,
            UserCollectionTable.collectionDescription,
            UserCollectionTable.collectionUrl,
            UserCollectionTable.collectionInvisibility,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            coalesce(
                count(
                    UserCollectionsLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count")
        ).orderBy(
            coalesce(
                count(
                    UserCollectionsLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count").desc()
        )
        .groupBy(
            UserCollectionTable.id,
            UserTable.userId
        )
}

fun Database.getAdminCollections(): Query {
    return this.from(AdminCollectionTable)
        .leftJoin(
            right = UserAdminCollectionLikeTable,
            on = AdminCollectionTable.id.eq(UserAdminCollectionLikeTable.collection_id)
        )
        .select(
            AdminCollectionTable.id,
            AdminCollectionTable.collectionName,
            AdminCollectionTable.collectionDescription,
            AdminCollectionTable.collectionUrl,
            AdminCollectionTable.collectionInvisibility,
            coalesce(
                count(
                    UserAdminCollectionLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count")
        )
        .orderBy(
            coalesce(
                count(
                    UserAdminCollectionLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count").desc()
        )
        .groupBy(
            AdminCollectionTable.id,
        )
}

fun Database.getLimitAdminCollections(limit: Int): Query {
    return this.from(AdminCollectionTable)
        .leftJoin(
            right = UserAdminCollectionLikeTable,
            on = AdminCollectionTable.id.eq(UserAdminCollectionLikeTable.collection_id)
        )
        .select(
            AdminCollectionTable.id,
            AdminCollectionTable.collectionName,
            AdminCollectionTable.collectionDescription,
            AdminCollectionTable.collectionUrl,
            AdminCollectionTable.collectionInvisibility,
            coalesce(
                count(
                    UserAdminCollectionLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count")
        )
        .orderBy(
            coalesce(
                count(
                    UserAdminCollectionLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count").desc()
        )
        .limit(limit)
        .groupBy(
            AdminCollectionTable.id,
        )
}

fun Database.getAllImageUserCollectionsByCollectionIdQuery(collectionId: Int, userId: Int): Query {
    return this.from(ImageDetailsTable)
        .innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
        )
        .innerJoin(
            right = ColorsTable,
            on = ImageDetailsTable.colorId.eq(ColorsTable.id)
        )
        .innerJoin(
            right = UserImageCollectionTable,
            on = ImageDetailsTable.id.eq(UserImageCollectionTable.image_id)
        ).innerJoin(
            right = UserCollectionTable,
            on = UserCollectionTable.id.eq(UserImageCollectionTable.collection_id)
        ).innerJoin(
            right = UserTable,
            on = UserTable.userId.eq(UserCollectionTable.userId)
        ).leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        ).select(
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
        ).where {
            UserCollectionTable.id.eq(collectionId)
        }
        .groupBy(
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            UserTable.userId,
            ImageUserLikesTable.image_id,
            ImageUserLikesTable.user_id
        ).orderBy(ImageDetailsTable.id.desc())
}

fun Database.getAllImageAdminCollectionsByCollectionIdQuery(
    collectionId: Int,
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
        .innerJoin(
            right = AdminImageCollectionTable,
            on = ImageDetailsTable.id.eq(AdminImageCollectionTable.image_id)
        ).innerJoin(
            right = AdminCollectionTable,
            on = AdminCollectionTable.id.eq(AdminImageCollectionTable.collection_id)
        ).leftJoin(
            right = ImageUserLikesTable,
            on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                    (ImageUserLikesTable.user_id.eq(userId))
        ).select(
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
        ).where {
            AdminCollectionTable.id.eq(collectionId)
        }
        .groupBy(
            ImageUserLikesTable.image_id,
            ImageDetailsTable.id,
            ImageCategoriesTable.id,
            ColorsTable.id,
            ImageUserLikesTable.user_id
        ).orderBy(ImageDetailsTable.id.desc())
}

fun Database.getUserCollectionDetails(collectionId: Int): Query {
    return this.from(UserCollectionTable)
        .innerJoin(
            right = UserTable,
            on = UserTable.userId.eq(UserCollectionTable.userId)
        )
        .leftJoin(
            right = UserCollectionsLikeTable,
            on = UserCollectionTable.id.eq(UserCollectionsLikeTable.collection_id)
        )
        .select(
            UserCollectionTable.id,
            UserCollectionTable.collectionName,
            UserCollectionTable.collectionDescription,
            UserCollectionTable.collectionUrl,
            UserCollectionTable.collectionInvisibility,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl,
            coalesce(
                count(
                    UserCollectionsLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count")
        ).where {
            UserCollectionTable.id.eq(collectionId)
        }
        .groupBy(
            UserCollectionTable.id,
            UserTable.userId
        ).limit(1)
}

fun Database.getAdminCollectionDetails(collectionId: Int): Query {
    return this.from(AdminCollectionTable)
        .leftJoin(
            right = UserAdminCollectionLikeTable,
            on = AdminCollectionTable.id.eq(UserAdminCollectionLikeTable.collection_id)
        )
        .select(
            AdminCollectionTable.id,
            AdminCollectionTable.collectionName,
            AdminCollectionTable.collectionDescription,
            AdminCollectionTable.collectionUrl,
            AdminCollectionTable.collectionInvisibility,
            coalesce(
                count(
                    UserAdminCollectionLikeTable.user_id
                ),
                defaultValue = 0
            ).aliased("like_count")
        ).where {
            AdminCollectionTable.id.eq(collectionId)
        }
        .groupBy(
            AdminCollectionTable.id,
        ).limit(1)
}