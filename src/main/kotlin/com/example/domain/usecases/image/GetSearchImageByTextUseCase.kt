package com.example.domain.usecases.image

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetSearchImageByTextUseCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(
        imageTitle: String,
        userId: Int
    ): BaseResponse<List<LiteImageDetailsDto>> {
        val imageList = mutableListOf<LiteImageDetailsDto>()

        val images = imageDao.searchImagesByImageTitle(
            userId = userId,
            imageTitle = imageTitle
        )

        if (images.isEmpty()) {
            val imagesFromTags = imageDao.getImageSearchResultByTagName(userId = userId, tagName = imageTitle)
            if (imagesFromTags.isNotEmpty()) {
                imageList.addAll(imagesFromTags)
                return BaseResponse.SuccessResponse(
                    message = ResponseMessages.SuccessFetchImageDetails.message,
                    data = imageList
                )
            }
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.EmptyFetchImages.message,
                data = images
            )
        }

        imageList.addAll(images)

        if (images.size <= 150) {
            val imagesFromTags = imageDao.getImageSearchResultByTagName(userId = userId, tagName = imageTitle)
            if (imagesFromTags.isNotEmpty()) {
                imageList.addAll(imagesFromTags)
            }
        }

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = imageList
        )
    }
}