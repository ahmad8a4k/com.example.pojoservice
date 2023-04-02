package com.example.domain.mapper

import com.example.data.dto.imageDetails.*
import com.example.data.tables.*
import com.example.utils.convertLongToDate
import org.ktorm.dsl.QueryRowSet
import java.sql.Date

fun QueryRowSet.imageFullDetailsToDto() = ImageDetailsFullDto(
    image_id = this[ImageDetailsTable.id] ?: 0,
    image_Title = this[ImageDetailsTable.imgTitle] ?: "Empty",
    image_url = this[ImageDetailsTable.url] ?: "Empty",
    category = ImageDetailsCategoryDto(
        category_id = this[ImageCategoriesTable.id] ?: 0,
        category_name = this[ImageCategoriesTable.categoryName] ?: "Empty"
    ),
    color = ImageDetailsColorDto(
        color_id = this[ColorsTable.id] ?: 0,
        color_name = this[ColorsTable.colorName] ?: "Empty"
    ),
    category_details = ImageDetailsCategoryDetailsDto(
        category_details_id = this[CategoryDetailsTable.id] ?: 0,
        category_details_name = this[CategoryDetailsTable.details] ?: "Empty"
    ),
    image_description = this[ImageDetailsTable.imgDescription] ?: "Empty",
    admin = ImageDetailsAdminDto(
        admin_id = this[AdminsTable.id] ?: 0,
        admin_name = this[AdminsTable.adminName] ?: "Empty"
    ),
    image_Stats_details = ImageDetailsImageStatsDetailsDto(
        stats_id = this[ImageStaImageStatsDetails.id] ?: 0,
        like_numbers = this[ImageStaImageStatsDetails.likesNum] ?: 0,
        watch_numbers = this[ImageStaImageStatsDetails.watchNum] ?: 0,
        download_numbers = this[ImageStaImageStatsDetails.downloadNum] ?: 0,
        save_numbers = this[ImageStaImageStatsDetails.saveNum] ?: 0,
        share_numbers = this[ImageStaImageStatsDetails.shareNum] ?: 0,
    ),
    register = Date.valueOf(this[ImageDetailsTable.register]).time.convertLongToDate()
)