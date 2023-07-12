package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
class UpdateUserLikedImage(
    private val imageDao: ImageDao
) {
    suspend operator fun invoke(userId: Int = 0, imageId: Int = 0): BaseResponse<Boolean> {

        if (userId == 0 || imageId == 0) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }

        val checkIfLiked = imageDao.checkIfUserLikedImageUseCase(userId = userId, imageId = imageId)

        return if(checkIfLiked){
            val removeLiked = imageDao.removeUserLikeImageUseCase(userId = userId, imageId = imageId)

            if (!removeLiked) {
                 BaseResponse.ErrorResponse(message = ResponseMessages.UnKnowFail.message, data = false)
            }
             BaseResponse.SuccessResponse(message = ResponseMessages.SuccessRemoveLikeImage.message, data = true)
        }else{
            val addLiked = imageDao.addUserLikeImageUseCase(userId = userId, imageId = imageId)
            if (!addLiked) {
                return BaseResponse.ErrorResponse(message = ResponseMessages.UnKnowFail.message, data = false)
            }
            return BaseResponse.SuccessResponse(message = ResponseMessages.SuccessAddLikeImage.message, data = true)
        }
    }
}