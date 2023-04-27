package com.example.data.source.dao

import com.example.data.dto.ColorDetailsDto
import com.example.data.dto.ImageDetailsDto
import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.dto.imageDetails.*
import org.ktorm.entity.Entity
import org.ktorm.schema.Table

interface ImageDao {

    suspend fun listOfImages(pageSize: Int, page: Int): List<ImageDetailsDto>

    suspend fun getImagesByPageSizeAndPageNumber(pageSize: Int, page: Int): List<ImageDetailsFullDto>

    suspend fun <T : Entity<T>> getCountOfTableItems(table: Table<T>): Int

    suspend fun <T : Entity<T>> getTotalPagesTable(table: Table<T>, pageSize: Int): Int

    suspend fun getSevenImageCategory(): List<ImageCategoryDto>

    suspend fun getAllCategoryImage(): List<ImageCategoryDto>

    suspend fun getFifteenImagesDetails(): List<ImageDetailsFullDto>

    suspend fun getPagingLiteImageDetails(pageSize: Int, page: Int): List<LiteImageDetailsDto>

    suspend fun getPagingLiteImageByDate(news: Boolean): List<LiteImageDetailsDto>

    suspend fun getTenTopRatedLiteImagesThisWeekORLastWeek(): List<LiteImageDetailsWithLikesCountDto>

    suspend fun getTopRatedLiteImages(pageSize: Int, pageNumber: Int): List<LiteImageDetailsWithLikesCountDto>

    suspend fun getAllColors(): List<ColorDetailsDto>

}