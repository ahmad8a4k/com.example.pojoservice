package com.example.data.source.dao

import com.example.data.dto.*
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

    suspend fun getTenTopRatedLiteImagesThisWeekORLastWeek(): List<LiteImageDetailsWithLikesCountAndTitleDto>

    suspend fun getTopRatedLiteImages(pageSize: Int, pageNumber: Int): List<LiteImageDetailsWithLikesCountAndTitleDto>

    suspend fun getAllColors(): List<ColorDetailsDto>

    suspend fun getAllLiteCategories(): List<ImageCategoryLiteDto>

    suspend fun getAllLiteImageByCategories(
        pageSize: Int,
        page: Int,
        categoryId: Int,
        categoryName: String,
    ): List<LiteImageDetailsWithLikesCountDto>

    suspend fun getAllLiteImage(): List<IdAndUrlImagesWithDto>


    /**
     *  For Admin In The Future
     */
    suspend fun updateBlurHashForImageByImageId(imageId: Int, blurHash: String)

    suspend fun updateCategoryBlurHashByCategoryId(categoryId: Int, blurHash: String)

    suspend fun getImagesDetailsBasedOnCategoryORColorId(
        categoryId: Int,
        colorID: Int,
    ): List<ImageDetailsWithLikesAndWatchAndUser>

    suspend fun getImageDetailsBasedOnImagedId(imageId: Int): ImageDetailsWithLikesAndWatchAndUser

    suspend fun getImagesDetailsBasedOnRandomCategoryID(limit: Int): List<ImageDetailsWithLikesAndWatchAndUser>
}