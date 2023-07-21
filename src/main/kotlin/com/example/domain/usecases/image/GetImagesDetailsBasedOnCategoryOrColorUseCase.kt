package com.example.domain.usecases.image

import com.example.data.dto.ImageDetailsWithLikesAndWatchAndUser
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetImagesDetailsBasedOnCategoryOrColorUseCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(
        imageId: Int,
        categoryId: Int,
        colorId: Int,
        userId: Int,
    ): BaseResponse<List<ImageDetailsWithLikesAndWatchAndUser>> {

        val imagesDetails = mutableListOf<ImageDetailsWithLikesAndWatchAndUser>()

        val imageDetails = imageDao.getImageDetailsBasedOnImagedId(imageId = imageId, userId = userId)

        if (imageDetails.image_id == 0) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.NotFoundImageByImageID.message)
        }

        val images = imageDao.getImagesDetailsBasedOnCategoryORColorId(
            categoryId = categoryId,
            colorID = colorId,
            userId = userId
        )

        if (images.isEmpty()) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchImages.message)
        }

        imagesDetails.addAll(images)

        imagesDetails.shuffled()

        if (images.size <= 58) {
            val imageDetailsRandom = imageDao.getImagesDetailsBasedOnRandomCategoryID(
                limit = 60 - images.size - 1,
                userId = userId
            )
            return if (imageDetailsRandom.isEmpty()) {
                BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchImages.message)
            } else {
                imagesDetails.addAll(imageDetailsRandom)
                imagesDetails.add(0, imageDetails)
                BaseResponse.SuccessResponse(
                    data = imagesDetails
                )
            }
        }

        imagesDetails.add(0, imageDetails)

        return BaseResponse.SuccessResponse(
            data = imagesDetails
        )
    }
}