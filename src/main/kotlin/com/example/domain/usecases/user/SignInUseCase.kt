package com.example.domain.usecases.user

import com.example.data.response.UserTokenResponse
import com.example.data.source.dao.UserDao
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateToken
import com.example.utils.verifyPassword

class SignInUseCase (
    private val userDao: UserDao
) {
    suspend operator fun invoke(userName: String, userPassword: String): BaseResponse<UserTokenResponse> {
        val userDto = userDao.getUserByUserName(userName = userName)

        if (userName.isEmpty()) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyField.message, data = UserTokenResponse())
        }

        if (userDto.user_name != userName) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.NotFoundUser.message,
                data = UserTokenResponse()
            )
        }

        val validationPassword =
            SaltedHash(hash = userDto.user_password, salt = userDto.user_salt).verifyPassword(userPassword)

        if (!validationPassword) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.IncorrectPassword.message,
                data = UserTokenResponse()
            )
        }

        return BaseResponse.SuccessResponse(data = UserTokenResponse(token = userDto.user_name.generateToken()))
    }
}