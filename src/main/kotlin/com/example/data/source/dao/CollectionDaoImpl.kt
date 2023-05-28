package com.example.data.source.dao

import com.example.data.dto.collections.CollectionDto
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.queries.getAdminCollections
import com.example.data.source.queries.getUsersCollections
import com.example.domain.queryMapper.collection.rowToAdminCollectionDto
import com.example.domain.queryMapper.collection.rowToUserCollectionDto
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.ktorm.database.Database
import org.ktorm.dsl.map

class CollectionDaoImpl(
    private var dataBase: Database,
) : CollectionDao {
    override suspend fun getUsersCollections(): List<CollectionWithUserDto> {
        return coroutineScope {
            val query = async { dataBase.getUsersCollections() }
            query.await().map { it.rowToUserCollectionDto() }
        }
    }

    override suspend fun getAdminCollections(): List<CollectionDto> {
        return coroutineScope {
            val query = async { dataBase.getAdminCollections() }
            query.await().map { it.rowToAdminCollectionDto() }
        }
    }

}