package com.example.data.source.dao

import com.example.data.dto.*
import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.dto.imageDetails.ImageCategoryLiteDto
import com.example.data.source.queries.*
import com.example.data.tables.*
import com.example.domain.queryMapper.images.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.Entity
import org.ktorm.schema.Column
import org.ktorm.schema.Table

class ImageDaoImpl(
    private var dataBase: Database,
) : ImageDao {

    override suspend fun listOfImages(pageSize: Int, page: Int): List<ImageDetailsDto> {
        return dataBase
            .from(ImageDetailsTable)
            .select()
            .limit(pageSize)
            .offset((page - 1) * pageSize)
            .map { it.toImageDetailsDto() }
    }

    override suspend fun <T : Entity<T>> getCountOfTableItems(table: Table<T>): Int {
        return dataBase.getCountOfTableItemsQuery(table = table)
    }

    override suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int {
        return dataBase.getTotalPagesTableQuery(columnName, pageSize)
    }

    override suspend fun getSevenImageCategory(): List<ImageCategoryDto> {
        return dataBase.from(ImageCategoriesTable)
            .leftJoin(
                AdminsTable,
                AdminsTable.id eq ImageCategoriesTable.adminAdded
            ).select()
            .limit(7).map { it.imageCategoryMapper() }
    }

    override suspend fun getAllCategoryImage(): List<ImageCategoryDto> {
        return dataBase.from(ImageCategoriesTable)
            .leftJoin(
                AdminsTable,
                AdminsTable.id eq ImageCategoriesTable.adminAdded
            ).select().map { it.imageCategoryMapper() }
    }

    override suspend fun getLiteImagesByDate(pageSize: Int, page: Int, userId: Int): List<LiteImageDetailsDto> {
        return coroutineScope {
            val query = async {
                dataBase.getLiteImagesOrderByDate(
                    pageSize = pageSize,
                    page = page,
                    userId = userId
                )
            }
            query.await().map { it.liteImageDetailsRow() }
        }
    }

    override suspend fun getTenTopRatedLiteImagesThreeWeeksAgo(limit: Int, userId: Int)
            : List<LiteImageDetailsDto> {
        return coroutineScope {
            val query = async {
                dataBase.getTopRatedLiteImagesThreeWeeksAgoQuery(
                    limit = limit,
                    userId = userId
                )
            }
            query.await().map { it.liteImageDetailsRow() }
        }
    }

    override suspend fun getTopRatedLiteImages(
        pageSize: Int,
        pageNumber: Int,
        userId: Int,
    ): List<LiteImageDetailsDto> {
        return coroutineScope {
            val query = async {
                dataBase.listOfTopRatedLiteImages(
                    pageSize = pageSize,
                    pageNumber = pageNumber,
                    userId = userId
                )
            }
            query.await().map { it.liteImageDetailsRow() }
        }
    }

    override suspend fun getAllLiteCategories(): List<ImageCategoryLiteDto> {
        return coroutineScope {
            val query = async { dataBase.getAllImagesCategoriesLite() }

            query.await().map { it.toCategoryLiteDto() }
        }
    }

    override suspend fun getAllLiteImageByCategories(
        pageSize: Int,
        page: Int,
        categoryId: Int,
        categoryName: String,
        userId: Int,
    ): List<LiteImageDetailsDto> {
        return coroutineScope {
            val query = async {
                dataBase.getAllLiteImagesByCategoryQuery(
                    pageSize = pageSize,
                    page = page,
                    categoryId = categoryId,
                    categoryName = categoryName,
                    userId = userId
                )
            }
            query.await().map { it.liteImageDetailsRow() }
        }
    }

    override suspend fun getAllLiteImage(): List<IdAndUrlImagesWithDto> {
        return coroutineScope {
            val query = async { dataBase.getIdAnUrlFromAllLiteImages() }

            query.await().map { it.idAndUrlImageMapperRow() }
        }
    }

    override suspend fun updateBlurHashForImageByImageId(imageId: Int, blurHash: String) {
        return coroutineScope {
            val query = async {
                dataBase.updateImageBlurHashByImageId(
                    imageId = imageId,
                    blurHash = blurHash
                )
            }
            query.await()
        }
    }

    override suspend fun updateCategoryBlurHashByCategoryId(categoryId: Int, blurHash: String) {
        return coroutineScope {
            val query = async {
                dataBase.updateCategoryBlurHashByCategoryId(
                    categoryId = categoryId,
                    blurHash = blurHash
                )
            }
            query.await()
        }
    }

    override suspend fun getImagesDetailsBasedOnCategoryORColorId(
        categoryId: Int,
        colorID: Int,
        userId: Int,
    ): List<ImageDetailsWithLikesAndWatchAndUser> {
        return coroutineScope {
            val q = async {
                dataBase.getImagesDetailsByColorIdAndCategoryIdQuery(
                    categoryID = categoryId,
                    colorId = colorID,
                    userId = userId
                )
            }
            q.await().map { it.imageDetailsWithLikeAndWatchCountRowMapper() }
        }
    }

    override suspend fun getImageDetailsBasedOnImagedId(imageId: Int, userId: Int):
            ImageDetailsWithLikesAndWatchAndUser {
        return coroutineScope {
            val q = async {
                dataBase.getImageDetailsByImageIdQuery(
                    imageId = imageId,
                    userId = userId
                )
            }
            q.await().map { it.imageDetailsWithLikeAndWatchCountRowMapper() }.first()
        }
    }

    override suspend fun getImagesDetailsBasedOnRandomCategoryID(
        limit: Int,
        userId: Int
    ): List<ImageDetailsWithLikesAndWatchAndUser> {
        return coroutineScope {
            val q = async {
                dataBase.getImagesDetailsBasedOnRandomCategoryIdQuery(
                    limit = limit,
                    userId = userId
                )
            }
            q.await().map { it.imageDetailsWithLikeAndWatchCountRowMapper() }
        }
    }

    override suspend fun checkIfUserLikedImageUseCase(userId: Int, imageId: Int): Boolean {
        return coroutineScope {
            val query = async {
                dataBase.checkIfUserLikedImage(userId = userId, imageId = imageId)
            }
            query.await().map {
                it[coalesce(
                    count(ImageUserLikesTable.user_id),
                    defaultValue = 0
                )
                    .aliased("like_count")]
            }.first() != 0
        }
    }

    override suspend fun addUserLikeImageUseCase(userId: Int, imageId: Int): Boolean {
        return coroutineScope {
            val query = async {
                dataBase.addUserLikeImage(userId = userId, imageId = imageId)
            }
            query.await() != 0
        }
    }

    override suspend fun removeUserLikeImageUseCase(userId: Int, imageId: Int): Boolean {
        return coroutineScope {
            val query = async {
                dataBase.removeUserLikeImage(userId = userId, imageId = imageId)
            }
            query.await() != 0
        }
    }

    override suspend fun updateImageWatchCount(imageId: Int): Boolean {
        return coroutineScope {
            val query = async {
                dataBase.updateWatchImage(imageId = imageId)
            }
            query.await() != 0
        }
    }
}