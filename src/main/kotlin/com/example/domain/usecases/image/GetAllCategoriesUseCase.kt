package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllCategoriesUseCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(): BaseResponse<List<ImageCategoryDto>> {

        val categories = imageDao.getAllCategoryImage()

        return if (categories.isNotEmpty()) BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = imageDao.getAllCategoryImage()
        )

        else BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchCategoryImages.message)
    }
}