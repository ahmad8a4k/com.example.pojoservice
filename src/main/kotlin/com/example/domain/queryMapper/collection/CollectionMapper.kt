package com.example.domain.queryMapper.collection

import com.example.data.dto.collections.CollectionDto
import com.example.data.tables.AdminCollectionTable
import org.ktorm.dsl.QueryRowSet

fun QueryRowSet.rowToAdminCollectionDto() = CollectionDto(
    collection_id = this[AdminCollectionTable.id] ?: 0,
    collection_name = this[AdminCollectionTable.collectionName] ?: "Empty",
    collection_description = this[AdminCollectionTable.collectionDescription] ?: "Empty",
    collection_invisibility = this[AdminCollectionTable.collectionInvisibility] ?: false,
    collection_url = this[AdminCollectionTable.collectionUrl] ?: "Empty",
    likesCount = this[AdminCollectionTable.likesCount] ?: 0
)
