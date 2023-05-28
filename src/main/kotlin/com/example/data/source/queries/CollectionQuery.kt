package com.example.data.source.queries

import com.example.data.tables.AdminCollectionTable
import com.example.data.tables.UserCollectionTable
import com.example.data.tables.UserTable
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.getUsersCollections(): Query {
    return this.from(UserCollectionTable)
        .innerJoin(
            right = UserTable,
            on = UserTable.userId.eq(UserCollectionTable.userId)
        )
        .select(
            UserCollectionTable.id,
            UserCollectionTable.collectionName,
            UserCollectionTable.collectionDescription,
            UserCollectionTable.collectionUrl,
            UserCollectionTable.likesCount,
            UserCollectionTable.collectionInvisibility,
            UserTable.userId,
            UserTable.userName,
            UserTable.userUrl
        )
        .orderBy(UserCollectionTable.likesCount.desc())
}

fun Database.getAdminCollections(): Query {
    return this.from(AdminCollectionTable)
        .select(
            AdminCollectionTable.id,
            AdminCollectionTable.collectionName,
            AdminCollectionTable.collectionDescription,
            AdminCollectionTable.collectionUrl,
            AdminCollectionTable.likesCount,
            AdminCollectionTable.collectionInvisibility,
        )
        .orderBy(AdminCollectionTable.likesCount.desc())
}