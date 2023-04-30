package com.example.data.source.queries

import com.example.data.dto.imageDetails.LiteNaturalDetailsDto
import com.example.data.dto.imageDetails.NaturalDetailsDto
import com.example.data.tables.*
import com.example.domain.queryMapper.natural.toLiteNaturalDetailsDto
import com.example.domain.queryMapper.natural.naturalFullDetailsToDto
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.naturalDetailsQuery(pageSize: Int, page: Int): List<NaturalDetailsDto> {
    return this.from(NaturalTable)
        .innerJoin(
            NaturalCategoryTable,
            NaturalCategoryTable.id eq NaturalTable.n_category_id
        )
        .innerJoin(
            ColorsTable,
            on = NaturalTable.color_id eq ColorsTable.id
        )
        .innerJoin(
            AdminsTable,
            on = AdminsTable.id eq NaturalTable.admin_id
        )
        .select(
            NaturalTable.id,
            NaturalTable.image_title,
            NaturalTable.url,
            NaturalTable.register,
            ColorsTable.id,
            ColorsTable.colorName,
            NaturalCategoryTable.id,
            NaturalCategoryTable.category_name,
            NaturalCategoryTable.category_url,
            NaturalCategoryTable.register,
            AdminsTable.id,
            AdminsTable.adminName,
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy(NaturalTable.register.asc())
        .map { it.naturalFullDetailsToDto() }
}


fun Database.getListOfLiteNaturalDetailsQuery(pageSize: Int, page: Int): List<LiteNaturalDetailsDto> {
    return this.from(NaturalTable)
        .select(
            ImageDetailsTable.id,
            ImageDetailsTable.url
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy()
        .map { it.toLiteNaturalDetailsDto() }
}

fun Database.getListOfLiteNaturalDetailsByCategoryQuery(
    pageSize: Int,
    page: Int,
    categoryName: String,
    categoryId: Int,
): List<LiteNaturalDetailsDto> {
    return this.from(NaturalTable)
        .innerJoin(
            right = NaturalCategoryTable,
            on = NaturalTable.n_category_id eq NaturalCategoryTable.id
        )
        .innerJoin(
            right = NaturalImagesTagsTable,
            on = NaturalTable.id eq NaturalImagesTagsTable.natural_id
        )
        .innerJoin(
            right = NaturalTagTable,
            on = NaturalImagesTagsTable.natural_tag_id eq NaturalTagTable.id
        )
        .selectDistinct(
            NaturalTable.id,
            NaturalTable.url
        )
        .where {
            (NaturalCategoryTable.id eq categoryId) or
                    (NaturalTagTable.tag_name.like("%$categoryName%"))
        }
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy(NaturalTable.id.asc())
        .map { it.toLiteNaturalDetailsDto() }
}

fun Database.getListOfLiteNaturalsByColorQuery(
    pageSize: Int,
    page: Int,
    colorName: String,
    colorId: Int,
): Query {
    return this.from(NaturalTable)
        .innerJoin(
            right = ColorsTable,
            on = NaturalTable.color_id eq ColorsTable.id
        )
        .innerJoin(
            right = NaturalImagesTagsTable,
            on = NaturalTable.id eq NaturalImagesTagsTable.natural_id
        )
        .innerJoin(
            right = NaturalTagTable,
            on = NaturalImagesTagsTable.natural_tag_id eq NaturalTagTable.id
        )
        .selectDistinct(
            NaturalTable.id,
            NaturalTable.url
        )
        .where {
            (ColorsTable.id eq colorId) or
                    (NaturalTagTable.tag_name.like("%$colorName%"))
        }
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy(NaturalTable.id.asc())
}

fun Database.getAllNaturalLiteImagesQuery(
    pageSize: Int,
    page: Int,
): Query {
    return this.from(NaturalTable)
        .selectDistinct(
            NaturalTable.id,
            NaturalTable.url
        )
        .limit(pageSize)
        .offset((page - 1) * pageSize)
        .orderBy(NaturalTable.register.asc())
}


