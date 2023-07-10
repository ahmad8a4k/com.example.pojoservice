package com.example.data.source.dao

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.dto.collections.CollectionDto
import com.example.data.dto.collections.CollectionWithUserDto

interface CollectionDao {
    suspend fun getUsersCollections(): List<CollectionWithUserDto>
    suspend fun getAdminCollections(): List<CollectionDto>
    suspend fun getImagesFromUsersCollectionsByCollectionId(collectionId: Int, userId:Int):
            List<LiteImageDetailsDto>
    suspend fun getImagesFromAdminsCollectionsByCollectionId(collectionId: Int, userId:Int): List<LiteImageDetailsDto>
    suspend fun getLimitAdminCollections(limit: Int): List<CollectionDto>
    suspend fun getUserCollectionDetails(collectionId:Int): CollectionWithUserDto
    suspend fun getAdminCollectionDetails(collectionId:Int): CollectionDto
}