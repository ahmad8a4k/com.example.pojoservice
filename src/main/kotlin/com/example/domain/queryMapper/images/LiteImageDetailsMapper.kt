package com.example.domain.queryMapper.images

import com.example.data.dto.*
import com.example.data.tables.*
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.isNotNull

fun QueryRowSet.liteImageDetailsRow() = LiteImageDetailsDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    likes_count = this[ImageDetailsTable.likeCount] ?: 0,
    blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
    category_id = this[ImageCategoriesTable.id] ?: 0,
    color_id = this[ColorsTable.id] ?: 0,
    color_hex = this[ColorsTable.colorHex] ?: "#2d3436",
    userLiked = this[ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")] ?: false,
    watch_count = this[ImageDetailsTable.watchCount] ?: 0
)

fun QueryRowSet.imageDetailsWithLikeAndWatchCountRowMapper() = ImageDetailsWithLikesAndWatchAndUser(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    image_title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_description = this[ImageDetailsTable.imgDescription] ?: "Empty",
    like_count = this[ImageDetailsTable.likeCount] ?: 0,
    watch_count = this[ImageDetailsTable.watchCount] ?: 0,
    blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
    user_id = this[UserTable.userId] ?: 0,
    user_name = this[UserTable.userName] ?: "Empty",
    user_url = this[UserTable.userUrl] ?: "Empty",
    user_liked = this[ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")] ?: false
)

fun QueryRowSet.idAndUrlImageMapperRow() = IdAndUrlImagesWithDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
)