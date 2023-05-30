package com.example.domain.queryMapper.collection

import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.queries.coalesce
import com.example.data.tables.UserCollectionTable
import com.example.data.tables.UserCollectionsLikeTable
import com.example.data.tables.UserTable
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.count

fun QueryRowSet.rowToUserCollectionDto() = CollectionWithUserDto(
    collection_id = this[UserCollectionTable.id] ?: 0,
    collection_name = this[UserCollectionTable.collectionName] ?: "Empty",
    collection_description = this[UserCollectionTable.collectionDescription] ?: "Empty",
    collection_invisibility = this[UserCollectionTable.collectionInvisibility] ?: false,
    collection_url = this[UserCollectionTable.collectionUrl] ?: "Empty",
    user_id = this[UserTable.userId] ?: 1,
    user_name = this[UserTable.userName] ?: "Empty",
    user_url = this[UserTable.userUrl] ?: "Empty",
    likesCount = this[coalesce(
        count(
            UserCollectionsLikeTable.user_id
        ),
        defaultValue = 0
    ).aliased("like_count")] as Int
)