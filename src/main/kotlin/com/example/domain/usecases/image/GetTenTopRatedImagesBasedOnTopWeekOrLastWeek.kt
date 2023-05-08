package com.example.domain.usecases.image

import com.example.data.dto.LiteImageDetailsWithLikesCountAndTitleDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetTenTopRatedImagesBasedOnTopWeekOrLastWeek(
    private val imagesDao: ImageDao,
) {
    suspend operator fun invoke(): BaseResponse<List<LiteImageDetailsWithLikesCountAndTitleDto>> {

        val theList = imagesDao.getTenTopRatedLiteImagesThisWeekORLastWeek()

        return if (theList.isNotEmpty())
            BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessFetchImageDetails.message,
                data = theList
            )
        else BaseResponse.ErrorLiseResponse(
            message = ResponseMessages.EmptyFetchCategoryImages.message
        )
    }
}