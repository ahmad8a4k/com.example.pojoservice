package com.example.domain.usecases.user

import com.example.data.response.UserResponseWithToken
import com.example.data.source.dao.UserDao
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateToken
import com.example.utils.verifyPassword

class UserSignInByUserNameUseCase(
    private val userDao: UserDao,
) {
    suspend operator fun invoke(userName: String, userPassword: String): BaseResponse<UserResponseWithToken> {

        if (
            userName.isEmpty() ||
            userPassword.isEmpty()
        ) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.EmptyField.message,
                data = UserResponseWithToken()
            )
        }

        if (!userDao.checkIfUserNameExist(userName)) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.NotFoundUser.message,
                data = UserResponseWithToken()
            )
        }

        val userDto = userDao.getUserByUserName(userName = userName)

        val validationPassword =
            SaltedHash(hash = userDto.user_password, salt = userDto.user_salt).verifyPassword(userPassword)

        if (!validationPassword) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.IncorrectPassword.message,
                data = UserResponseWithToken()
            )
        }

        return BaseResponse.SuccessResponse(
            data = UserResponseWithToken(
                token = userDto.user_id.generateToken(),
                userID = userDto.user_id,
                userName = userDto.user_name
            ),
            message = ResponseMessages.SuccessSignIn.message
        )
    }
}