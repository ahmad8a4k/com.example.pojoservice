package com.example.data.source.dao

import com.example.data.dto.imageDetails.NaturalCategoriesDto
import com.example.data.dto.imageDetails.NaturalDetailsDto
import com.example.data.tables.NaturalCategoryTable
import com.example.domain.mapper.naturalCategoryDto
import com.example.utils.naturalDetailsQuery
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select

class NaturalImagesDaoImpl (
    private var dataBase: Database,
) : NaturalImagesDao {

    override suspend fun naturalImagesByPageSizeAndPageNumber(pageSize: Int, page: Int): List<NaturalDetailsDto> {
        return dataBase.naturalDetailsQuery(pageSize = pageSize, page = page)
    }

    override suspend fun getNaturalCategories(): List<NaturalCategoriesDto> {
        return dataBase.from(NaturalCategoryTable).select().map { it.naturalCategoryDto() }
    }
}