package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.BaseResponse.ErrorResponse
import com.example.utils.ResponseMessages
class AddUserLikeImageUseCase(
    private val imageDao: ImageDao
) {
    suspend operator fun invoke(userId: Int = 0, imageId: Int = 0): BaseResponse<Boolean> {
        if (userId == 0 || imageId == 0) {
            return ErrorResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }
        val addLiked = imageDao.addUserLikeImageUseCase(userId = userId, imageId = imageId)
        if (!addLiked) {
            return ErrorResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        return BaseResponse.SuccessResponse(message = ResponseMessages.SuccessAddLikeImage.message, data = true)
    }
}