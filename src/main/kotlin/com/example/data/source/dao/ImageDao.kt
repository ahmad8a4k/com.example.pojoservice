package com.example.data.source.dao

import com.example.data.dto.*
import com.example.data.dto.imageDetails.*
import org.ktorm.entity.Entity
import org.ktorm.schema.Column
import org.ktorm.schema.Table

interface ImageDao {

    suspend fun listOfImages(pageSize: Int, page: Int): List<ImageDetailsDto>

    suspend fun <T : Entity<T>> getCountOfTableItems(table: Table<T>): Int

    suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int

    suspend fun getSevenImageCategory(): List<ImageCategoryDto>

    suspend fun getAllCategoryImage(): List<ImageCategoryDto>

    suspend fun getLiteImagesByDate(pageSize: Int, page: Int, userId: Int): List<LiteImageDetailsDto>

    suspend fun getTenTopRatedLiteImagesThreeWeeksAgo(limit: Int, userId: Int): List<LiteImageDetailsDto>

    suspend fun getTopRatedLiteImages(pageSize: Int, pageNumber: Int, userId: Int):
            List<LiteImageDetailsDto>

    suspend fun searchImagesByImageTitle(userId: Int, imageTitle: String): List<LiteImageDetailsDto>
    suspend fun getImageSearchResultByTagName(userId: Int, tagName: String): List<LiteImageDetailsDto>

    suspend fun getAllLiteCategories(): List<ImageCategoryLiteDto>

    suspend fun getAllLiteImageByCategories(
        pageSize: Int,
        page: Int,
        categoryId: Int,
        categoryName: String,
        userId: Int,
    ): List<LiteImageDetailsDto>

    suspend fun getAllLiteImage(): List<IdAndUrlImagesWithDto>


    /**
     *  For Admin In The Future
     */
    suspend fun updateBlurHashForImageByImageId(imageId: Int, blurHash: String)

    suspend fun updateCategoryBlurHashByCategoryId(categoryId: Int, blurHash: String)

    suspend fun getImagesDetailsBasedOnCategoryORColorId(
        categoryId: Int,
        colorID: Int,
        userId: Int,
    ): List<ImageDetailsWithLikesAndWatchAndUser>

    suspend fun getImageDetailsBasedOnImagedId(imageId: Int, userId: Int): ImageDetailsWithLikesAndWatchAndUser

    suspend fun getImagesDetailsBasedOnRandomCategoryID(
        limit: Int,
        userId: Int,
    ): List<ImageDetailsWithLikesAndWatchAndUser>

    suspend fun checkIfUserLikedImageUseCase(userId: Int, imageId: Int): Boolean
    suspend fun addUserLikeImageUseCase(userId: Int, imageId: Int): Boolean
    suspend fun removeUserLikeImageUseCase(userId: Int, imageId: Int): Boolean
    suspend fun updateImageWatchCount(imageId: Int): Boolean
    suspend fun updateLikedImageCountByIncrease(imageId: Int): Boolean
    suspend fun updateLikedImageCountByDecrease(imageId: Int): Boolean
}