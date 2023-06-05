package com.example.data.source.dao

import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.dto.LiteImageDetailsWithLiteUserInformationDto
import com.example.data.dto.collections.CollectionDto
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.queries.*
import com.example.domain.queryMapper.collection.rowToAdminCollectionDto
import com.example.domain.queryMapper.collection.rowToUserCollectionDto
import com.example.domain.queryMapper.images.liteImageDetailsWithLikeCountRow
import com.example.domain.queryMapper.images.liteImageDetailsWithLikesCountRow
import com.example.domain.queryMapper.images.liteImageDetailsWithLiteUserInformationRow
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

    override suspend fun getImagesFromUsersCollectionsByCollectionId(collectionId: Int):
            List<LiteImageDetailsWithLikesCountDto> {
        return coroutineScope {
            val query = async {
                dataBase.getAllImageUserCollectionsByCollectionIdQuery(collectionId = collectionId)
            }
            query.await().map { it.liteImageDetailsWithLikeCountRow() }
        }
    }

    override suspend fun getImagesFromAdminsCollectionsByCollectionId(collectionId: Int):
            List<LiteImageDetailsWithLikesCountDto> {
        return coroutineScope {
            val query = async {
                dataBase.getAllImageAdminCollectionsByCollectionIdQuery(collectionId = collectionId)
            }
            query.await().map { it.liteImageDetailsWithLikeCountRow() }
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
}