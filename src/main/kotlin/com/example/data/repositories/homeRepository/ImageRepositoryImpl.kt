package com.example.data.repositories.homeRepository

import com.example.data.dto.ImageDetailsDto
import com.example.data.source.dao.ImageDao

class ImageRepositoryImpl(
    private val imageDao : ImageDao
): ImageRepository {

    override suspend fun listOfImages(pageSize: Int, page: Int): List<ImageDetailsDto>{
        return imageDao.listOfImages(pageSize, page)
    }

}