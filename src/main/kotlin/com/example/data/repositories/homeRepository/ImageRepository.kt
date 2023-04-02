package com.example.data.repositories.homeRepository

import com.example.data.dto.ImageDetailsDto

interface ImageRepository {
    suspend fun listOfImages(pageSize: Int, page: Int): List<ImageDetailsDto>



}