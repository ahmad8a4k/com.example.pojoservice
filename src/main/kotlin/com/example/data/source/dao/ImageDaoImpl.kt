package com.example.data.source.dao

import com.example.data.dto.ImageDetailsDto
import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.source.queries.*
import com.example.data.tables.*
import com.example.domain.queryMapper.images.imageCategoryMapper
import com.example.domain.queryMapper.images.imageFullDetailsToDto
import com.example.domain.queryMapper.images.toImageDetailsDto
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.Entity
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

    override suspend fun getImagesByPageSizeAndPageNumber(pageSize: Int, page: Int): List<ImageDetailsFullDto> {
        return dataBase.imageFullDetailsQuery(pageSize = pageSize, page = page)
    }

    override suspend fun <T : Entity<T>> getCountOfTableItems(table: Table<T>): Int {
        return dataBase.getCountOfTableItemsQuery(table = table)
    }

    override suspend fun <T : Entity<T>> getTotalPagesTable(table: Table<T>, pageSize: Int): Int {
        return dataBase.getTotalPagesTableQuery(table, pageSize)
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

    override suspend fun getFifteenImagesDetails(): List<ImageDetailsFullDto> {
        return dataBase.from(ImageDetailsTable).select().limit(15).map { it.imageFullDetailsToDto() }
    }

    override suspend fun getPagingLiteImageDetails(pageSize: Int, page: Int): List<LiteImageDetailsDto> {
        return dataBase.liteListImageDetailsQuery(pageSize = pageSize, page = page)
    }

    override suspend fun getPagingLiteImageByDate(news: Boolean): List<LiteImageDetailsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getTenTopRatedLiteImagesThisWeekORLastWeek(): List<LiteImageDetailsWithLikesCountDto> {
        return dataBase.getTopRatedLiteImagesThisWeekOrLastWeeks()
    }

    override suspend fun getTopRatedLiteImages(
        pageSize: Int,
        pageNumber: Int,
    ): List<LiteImageDetailsWithLikesCountDto> {
        return dataBase.listOfTopRatedLiteImages(pageSize = pageSize, pageNumber = pageNumber)
    }
}