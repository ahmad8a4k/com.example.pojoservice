package com.example.domain.usecases.user

import com.example.data.request.UpdateUserPasswordRequest
import com.example.data.source.dao.UserDao
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateSaltedHash
import com.example.utils.verifyPassword

class UpdateUserPasswordUseCase(
    private val userDao: UserDao,
) {
    suspend operator fun invoke(parameters: UpdateUserPasswordRequest): BaseResponse<Boolean> {

        if (
            parameters.password.isEmpty() ||
            parameters.newPassword.isEmpty() ||
            parameters.username.isEmpty()
        ) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyField.message, data = false)
        }

        val userDto = userDao.getUserByUserName(userName = parameters.username)
            ?: return BaseResponse.ErrorResponse(message = ResponseMessages.NotFoundUser.message, data = false)

        if (userDto.user_name == "Empty") {
            return BaseResponse.ErrorResponse(message = ResponseMessages.NotFoundUser.message, data = false)
        }

        val validationPassword = SaltedHash(hash = userDto.user_password, salt = userDto.user_salt)
            .verifyPassword(parameters.password)

        if (!validationPassword) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.IncorrectPassword.message, data = false)
        }

        val saltedHash = parameters.newPassword.generateSaltedHash()
        userDao.updateUserPassword(
            user = userDto.copy(
                user_password = saltedHash.hash, user_salt = saltedHash.salt
            )
        )

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessUpdatePassword.message, data = true
        )
    }
}