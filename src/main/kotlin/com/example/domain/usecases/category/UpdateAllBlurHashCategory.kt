package com.example.domain.usecases.category

import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.encodeImageToBlurHashUsingURl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UpdateAllBlurHashCategory(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke() {

        val categories = imageDao.getAllLiteCategories()

        return if (categories.isNotEmpty()) {
            withContext(Dispatchers.IO) {
                categories.forEach { categories ->
                    delay(5000)
                    imageDao.updateCategoryBlurHashByCategoryId(
                        categoryId = categories.category_id,
                        blurHash = categories.category_url.encodeImageToBlurHashUsingURl()
                    )
                    delay(2000)
                }
            }
        } else return

    }
}