package com.example.domain.queryMapper.collection

import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.tables.UserCollectionTable
import com.example.data.tables.UserTable
import org.ktorm.dsl.QueryRowSet

fun QueryRowSet.rowToUserCollectionDto() = CollectionWithUserDto(
    collection_id = this[UserCollectionTable.id] ?: 0,
    collection_name = this[UserCollectionTable.collectionName] ?: "Empty",
    collection_description = this[UserCollectionTable.collectionDescription] ?: "Empty",
    collection_invisibility = this[UserCollectionTable.collectionInvisibility] ?: false,
    collection_url = this[UserCollectionTable.collectionUrl] ?: "Empty",
    likesCount = this[UserCollectionTable.likesCount] ?: 0,
    user_id = this[UserTable.userId] ?: 1,
    user_name = this[UserTable.userName] ?: "Empty",
    user_url = this[UserTable.userUrl] ?: "Empty"
)