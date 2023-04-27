package com.example.domain.usecases.image

import com.example.data.dto.ColorDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllColorsUseCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(): BaseResponse<List<ColorDetailsDto>> {

        val colors = imageDao.getAllColors()

        return if (colors.isNotEmpty()) BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = colors
        )
        else BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchColorsImages.message)

    }
}