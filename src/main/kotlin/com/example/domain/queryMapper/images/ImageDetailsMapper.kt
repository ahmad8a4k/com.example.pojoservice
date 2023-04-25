package com.example.domain.queryMapper.images

import com.example.data.dto.ImageDetailsDto
import com.example.data.dto.UserDto
import com.example.data.tables.ImageDetailsTable
import com.example.data.tables.UserTable
import com.example.utils.convertLongToDate
import org.ktorm.dsl.QueryRowSet
import java.sql.Date

fun QueryRowSet.toImageDetailsDto() = ImageDetailsDto(
    id = this[ImageDetailsTable.id] ?: 0,
    imgTitle = this[ImageDetailsTable.imgTitle] ?: "Empty",
    url = this[ImageDetailsTable.url] ?: "Empty",
    categoryId = this[ImageDetailsTable.categoryId] ?: 0,
    colorId = this[ImageDetailsTable.colorId] ?: 0,
    categoryDetailsId = this[ImageDetailsTable.categoryDetailsId] ?: 0,
    imgDescription = this[ImageDetailsTable.imgDescription] ?: "Empty",
    adminId = this[ImageDetailsTable.adminId] ?: 0,
    register = Date.valueOf(this[ImageDetailsTable.register]).time.convertLongToDate()
)