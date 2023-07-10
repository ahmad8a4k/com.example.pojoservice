package com.example.data.source.dao

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.collections.CollectionDto
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.queries.*
import com.example.domain.queryMapper.collection.rowToAdminCollectionDto
import com.example.domain.queryMapper.collection.rowToUserCollectionDto
import com.example.domain.queryMapper.images.liteImageDetailsRow
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
    override suspend fun getImagesFromUsersCollectionsByCollectionId(collectionId: Int, userId: Int):
            List<LiteImageDetailsDto> {
        return coroutineScope {
            val query = async {
                dataBase.getAllImageUserCollectionsByCollectionIdQuery(
                    collectionId = collectionId,
                    userId = userId
                )
            }
            query.await().map { it.liteImageDetailsRow() }
        }
    }
    override suspend fun getImagesFromAdminsCollectionsByCollectionId(collectionId: Int, userId: Int):
            List<LiteImageDetailsDto> {
        return coroutineScope {
            val query = async {
                dataBase.getAllImageAdminCollectionsByCollectionIdQuery(
                    collectionId = collectionId,
                    userId = userId
                )
            }
            query.await().map { it.liteImageDetailsRow() }
        }
    }
    override suspend fun getLimitAdminCollections(limit: Int): List<CollectionDto> {
        return coroutineScope {
            val query = async {
                dataBase.getLimitAdminCollections(limit = limit)
            }
            query.await().map { it.rowToAdminCollectionDto() }
        }
    }
    override suspend fun getUserCollectionDetails(collectionId: Int): CollectionWithUserDto {
        return coroutineScope {
            val query = async {
                dataBase.getUserCollectionDetails(collectionId = collectionId)
            }
            query.await().map { it.rowToUserCollectionDto() }.first()
        }
    }
    override suspend fun getAdminCollectionDetails(collectionId: Int): CollectionDto {
        return coroutineScope {
            val query = async {
                dataBase.getAdminCollectionDetails(collectionId = collectionId)
            }
            query.await().map { it.rowToAdminCollectionDto() }.first()
        }
    }
}