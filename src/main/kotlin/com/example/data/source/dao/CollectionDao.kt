package com.example.data.source.dao

import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.dto.collections.CollectionDto
import com.example.data.dto.collections.CollectionWithUserDto

interface CollectionDao {
    suspend fun getUsersCollections(): List<CollectionWithUserDto>
    suspend fun getAdminCollections(): List<CollectionDto>
    suspend fun getImagesFromUsersCollectionsByCollectionId(collectionId:Int): List<LiteImageDetailsWithLikesCountDto>
    suspend fun getImagesFromAdminsCollectionsByCollectionId(collectionId:Int): List<LiteImageDetailsWithLikesCountDto>
}