package com.example.domain.queryMapper.images

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.tables.ImageDetailsTable
import com.example.data.tables.UserSocialTable
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.count

fun QueryRowSet.liteImageDetailsRow() = LiteImageDetailsDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_Title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_url = this[ImageDetailsTable.url] ?: "Empty"
)

fun QueryRowSet.liteImageDetailsWithLikesCountRow() = LiteImageDetailsWithLikesCountDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_Title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    likes_count = this[count(UserSocialTable.image_details_id).aliased("likes_count")] ?: 0
)