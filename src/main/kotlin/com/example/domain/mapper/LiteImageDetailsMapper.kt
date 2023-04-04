package com.example.domain.mapper

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.tables.ImageDetailsTable
import com.example.utils.convertLongToDate
import org.ktorm.dsl.QueryRowSet
import java.sql.Date

fun QueryRowSet.liteImageDetails() = LiteImageDetailsDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_Title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    image_description = this[ImageDetailsTable.imgDescription] ?: "Empty",
    register = Date.valueOf(this[ImageDetailsTable.register]).time.convertLongToDate()
)