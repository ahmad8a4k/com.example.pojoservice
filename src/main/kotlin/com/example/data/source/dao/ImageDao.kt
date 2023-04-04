package com.example.data.source.dao

import com.example.data.dto.ImageDetailsDto
import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.dto.imageDetails.NaturalCategoriesDto
import com.example.data.dto.imageDetails.NaturalDetailsDto
import com.example.data.tables.NaturalCategoryTable
import org.ktorm.entity.Entity
import org.ktorm.schema.Table

interface ImageDao {

    suspend fun listOfImages(pageSize: Int, page: Int): List<ImageDetailsDto>

    suspend fun imagesByPageSizeAndPageNumber(pageSize: Int, page: Int): List<ImageDetailsFullDto>

    suspend fun <T : Entity<T>> getCountOfTableItems(table: Table<T>): Int

    suspend fun <T : Entity<T>> getTotalPagesTable(table: Table<T>, pageSize: Int): Int

    suspend fun getSevenImageCategory(): List<ImageCategoryDto>

    suspend fun getAllCategoryImage(): List<ImageCategoryDto>

    suspend fun getFifteenImagesDetails(): List<ImageDetailsFullDto>

    suspend fun getLiteImageDetailsByPaging(pageSize: Int, page: Int): List<LiteImageDetailsDto>
}