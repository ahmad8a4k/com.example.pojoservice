package com.example.data.source.dao

import com.example.data.dto.imageDetails.LiteNaturalDetailsDto
import com.example.data.dto.imageDetails.NaturalCategoriesDto
import com.example.data.dto.imageDetails.NaturalDetailsDto
import com.example.data.source.queries.*
import com.example.data.tables.NaturalCategoryTable
import com.example.domain.queryMapper.natural.toLiteNaturalDetailsDto
import com.example.domain.queryMapper.natural.naturalCategoryDto
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select

class NaturalImagesDaoImpl(
    private var dataBase: Database,
) : NaturalImagesDao {

    override suspend fun getNaturalImagesByPageSizeAndPageNumber(pageSize: Int, page: Int): List<NaturalDetailsDto> {
        return dataBase.naturalDetailsQuery(pageSize = pageSize, page = page)
    }

    override suspend fun getNaturalCategories(): List<NaturalCategoriesDto> {
        return dataBase.from(NaturalCategoryTable).select().map { it.naturalCategoryDto() }
    }

    //New
    override suspend fun getLiteNaturalDetails(pageSize: Int, page: Int): List<LiteNaturalDetailsDto> {
        return dataBase.getListOfLiteNaturalDetailsQuery(pageSize = pageSize, page = page)
    }

    override suspend fun getLitesNaturalsByNaturalCategoryPagingAnd(
        pageSize: Int,
        page: Int,
        categoryId: Int,
        categoryName: String,
    ): List<LiteNaturalDetailsDto> {
        return dataBase.getListOfLiteNaturalDetailsByCategoryQuery(
            pageSize = pageSize,
            page = page,
            categoryName = categoryName,
            categoryId = categoryId
        )
    }

    override suspend fun getLitesNaturalsByNaturalColorPagingAnd(
        pageSize: Int,
        page: Int,
        colorId: Int,
        colorName: String,
    ): List<LiteNaturalDetailsDto> =
        coroutineScope {
            val query = async {
                dataBase.getListOfLiteNaturalsByColorQuery(
                    pageSize = pageSize,
                    page = page,
                    colorName = colorName,
                    colorId = colorId,
                )
            }
            query.await().map { it.toLiteNaturalDetailsDto() }
        }

    override suspend fun getAllNaturalLiteImages(
        pageSize: Int,
        page: Int,
    ): List<LiteNaturalDetailsDto> =
        coroutineScope {
            val query = async {
                dataBase.getAllNaturalLiteImagesQuery(
                    pageSize = pageSize,
                    page = page,
                )
            }

            query.await().map { it.toLiteNaturalDetailsDto() }
        }

}