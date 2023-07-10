package com.example.data.request

data class ImageCategoryColorRequest(
    val imageId: Int,
    val categoryId: Int,
    val colorId: Int,
    val userId: Int
)
