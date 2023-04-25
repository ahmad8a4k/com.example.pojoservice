package com.example.domain.usecases.natural

import com.example.data.dto.imageDetails.NaturalCategoriesDto
import com.example.data.source.dao.NaturalImagesDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllNaturalCategoriesUseCase(
    private val naturalImagesDao: NaturalImagesDao,
) {
    suspend operator fun invoke(): BaseResponse<List<NaturalCategoriesDto>> {

        val categories = naturalImagesDao.getNaturalCategories()

        return if (categories.isNotEmpty()) BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = categories
        )
        else BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchCategoryImages.message)
    }
}