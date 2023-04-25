package com.example.domain.usecases.user

import com.example.data.dto.UserDto
import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetUserDetailsByIDUserCase(
    private val userDao: UserDao,
) {
    suspend operator fun invoke(userId: Int): BaseResponse<UserDto> {

        if (userId == 0) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.NotFoundUserID.message,
                data = UserDto()
            )
        }

        if (!userDao.checkIfUserExistOrNotByUserId(userId = userId)) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.NotFoundUserID.message,
                data = UserDto()
            )
        }

        val userDetails = userDao.getUserInformationByUserId(userId = userId)

        if (userDetails.user_id == 0) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.NotFoundUserID.message,
                data = UserDto()
            )
        }

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchUserByID.message,
            data = userDetails
        )
    }
}