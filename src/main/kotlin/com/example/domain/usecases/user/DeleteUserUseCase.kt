package com.example.domain.usecases.user

import com.example.data.dto.UserDto
import com.example.data.source.dao.UserDao
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.verifyPassword

class DeleteUserUseCase (
    private val userDao: UserDao
) {

    suspend operator fun invoke(userName: String, userPassword: String): BaseResponse<UserDto> {
        val userDto = userDao.getUserByUserName(userName = userName)

        if (userName.isEmpty()) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyField.message, data = UserDto())
        }

        if (userDto.user_name != userName) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.NotFoundUser.message, data = UserDto())
        }

        val validationPassword =
            SaltedHash(hash = userDto.user_password, salt = userDto.user_salt).verifyPassword(userPassword)

        if (!validationPassword) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.IncorrectPassword.message, data = UserDto())
        }

        userDao.deleteUser(userName)

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessDeleteUser.message,
            data = true
        ) as BaseResponse<UserDto>

    }
}