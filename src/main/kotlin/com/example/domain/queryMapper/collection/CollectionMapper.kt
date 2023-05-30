package com.example.domain.queryMapper.collection

import com.example.data.dto.collections.CollectionDto
import com.example.data.source.queries.coalesce
import com.example.data.tables.AdminCollectionTable
import com.example.data.tables.UserAdminCollectionLikeTable
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.count

fun QueryRowSet.rowToAdminCollectionDto() = CollectionDto(
    collection_id = this[AdminCollectionTable.id] ?: 0,
    collection_name = this[AdminCollectionTable.collectionName] ?: "Empty",
    collection_description = this[AdminCollectionTable.collectionDescription] ?: "Empty",
    collection_invisibility = this[AdminCollectionTable.collectionInvisibility] ?: false,
    collection_url = this[AdminCollectionTable.collectionUrl] ?: "Empty",
    likesCount = this[coalesce(
        count(
            UserAdminCollectionLikeTable.user_id
        ),
        defaultValue = 0
    ).aliased("like_count")] as Int
)
