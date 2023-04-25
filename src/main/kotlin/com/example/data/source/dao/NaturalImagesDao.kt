package com.example.data.source.dao

import com.example.data.dto.imageDetails.LiteNaturalDetailsDto
import com.example.data.dto.imageDetails.NaturalCategoriesDto
import com.example.data.dto.imageDetails.NaturalDetailsDto

interface NaturalImagesDao {

    suspend fun getNaturalImagesByPageSizeAndPageNumber(pageSize: Int, page: Int): List<NaturalDetailsDto>

    suspend fun getNaturalCategories(): List<NaturalCategoriesDto>

    suspend fun getLiteNaturalDetails(pageSize: Int, page: Int): List<LiteNaturalDetailsDto>

    suspend fun getLitesNaturalsByNaturalCategoryPagingAnd(
        pageSize: Int,
        page: Int,
        categoryId: Int,
        categoryName: String,
    ): List<LiteNaturalDetailsDto>

    suspend fun getLitesNaturalsByNaturalColorPagingAnd(
        pageSize: Int,
        page: Int,
        colorId: Int,
        colorName: String,
    ): List<LiteNaturalDetailsDto>

}