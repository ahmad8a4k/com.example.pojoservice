package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao
import com.example.utils.encodeImageToBlurHashUsingURl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


/**
 * This Just For Experimental Feature
 */
class UpdateBlurHashForLiteImagesByCategoryId(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke() {

        val images = imageDao.getAllLiteImage()

        withContext(Dispatchers.IO) {
            images.forEach { image ->
                delay(5000)
                imageDao.updateBlurHashForImageByImageId(
                    imageId = image.image_id,
                    blurHash = image.image_url.encodeImageToBlurHashUsingURl()
                )
                delay(2000)
            }
        }
    }
}