package com.example.domain.queryMapper.images

import com.example.data.dto.*
import com.example.data.source.queries.coalesce
import com.example.data.tables.*
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.count
import org.ktorm.schema.decimal

fun QueryRowSet.liteImageDetailsRow() = LiteImageDetailsDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_Title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_url = this[ImageDetailsTable.url] ?: "Empty"
)

fun QueryRowSet.liteImageDetailsWithLikesCountRow() = LiteImageDetailsWithLikesCountAndTitleDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_Title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    likes_count = this[count(UserSocialTable.image_details_id).aliased("likes_count")] ?: 0
)

fun QueryRowSet.liteImageDetailsWithLikeCountRow() = LiteImageDetailsWithLikesCountDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    likes_count = this[coalesce(count(ImageUserLikesTable.user_id), 0).aliased("like_count")] as Int,
    blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
    category_id = this[ImageCategoriesTable.id] ?: 0,
    color_id = this[ColorsTable.id] ?: 0,
    color_hex = this[ColorsTable.colorHex] ?: "#2d3436"
)

fun QueryRowSet.liteImageDetailsWithLiteUserInformationRow() = LiteImageDetailsWithLiteUserInformationDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    likes_count = this[coalesce(count(ImageUserLikesTable.user_id), 0).aliased("like_count")] as Int,
    blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
    category_id = this[ImageCategoriesTable.id] ?: 0,
    color_id = this[ColorsTable.id] ?: 0,
    color_hex = this[ColorsTable.colorHex] ?: "#2d3436",
    user_id = this[UserTable.userId] ?: 0,
    user_name = this[UserTable.userName] ?: "Empty",
    user_url = this[UserTable.userUrl] ?: "Empty"
)


fun QueryRowSet.imageDetailsWithLikeAndWatchCountRowMapper() = ImageDetailsWithLikesAndWatchAndUser(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    image_title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_description = this[ImageDetailsTable.imgDescription] ?: "Empty",
    like_count = this[coalesce(count(ImageUserLikesTable.user_id), 0).aliased("like_count")] as Int,
    watch_count = this[coalesce(count(ImageUserLikesTable.user_id), 0).aliased("watch_count")] as Int,
    blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
    user_id = this[UserTable.userId] ?: 0,
    user_name = this[UserTable.userName] ?: "Empty",
    user_url = this[UserTable.userUrl] ?: "Empty"
)

fun QueryRowSet.idAndUrlImageMapperRow() = IdAndUrlImagesWithDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
)