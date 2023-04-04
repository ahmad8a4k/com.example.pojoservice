package com.example.data.source.dao

import com.example.data.dto.imageDetails.NaturalCategoriesDto
import com.example.data.dto.imageDetails.NaturalDetailsDto

interface NaturalImagesDao {

    suspend fun naturalImagesByPageSizeAndPageNumber(pageSize: Int, page: Int): List<NaturalDetailsDto>

    suspend fun getNaturalCategories(): List<NaturalCategoriesDto>

}