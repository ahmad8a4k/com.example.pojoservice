package com.example.domain.usecases.user

import com.example.data.response.UserTokenResponse
import com.example.data.source.dao.UserDao
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateToken
import com.example.utils.verifyPassword

class UserSignInByUserEmailUserCase(
    private val userDao: UserDao,
) {
    suspend operator fun invoke(email: String, userPassword: String): BaseResponse<UserTokenResponse> {

        if (
            email.isEmpty() ||
            userPassword.isEmpty()
        ) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.EmptyField.message,
                data = UserTokenResponse()
            )
        }

        if (!userDao.checkIfUserEmailExist(email)) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.NotFoundUserEmail.message,
                data = UserTokenResponse()
            )
        }

        val userDto = userDao.getUserByUserEmail(email = email)

        val validationPassword =
            SaltedHash(hash = userDto.user_password, salt = userDto.user_salt).verifyPassword(userPassword)

        if (!validationPassword) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.IncorrectPassword.message,
                data = UserTokenResponse()
            )
        }

        return BaseResponse.SuccessResponse(
            data = UserTokenResponse(
                token = userDto.user_id.generateToken()
            ),
            message = ResponseMessages.SuccessSignIn.message
        )
    }
}