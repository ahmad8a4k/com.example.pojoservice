package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetFifteenImagesDetailsUseCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(): BaseResponse<List<ImageDetailsFullDto>> {

        val images = imageDao.getFifteenImagesDetails()

        return if (images.isNotEmpty()) BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = imageDao.getFifteenImagesDetails()
        )

        else BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchCategoryImages.message)
    }
}