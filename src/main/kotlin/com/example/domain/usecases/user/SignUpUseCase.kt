package com.example.domain.usecases.user

import com.example.data.dto.UserDto
import com.example.data.repositories.userRepository.UserRepository
import com.example.data.response.UserResponseWithToken
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateToken

class SignUpUseCase constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: UserDto): BaseResponse<UserResponseWithToken> {
        if (
            user.user_email.isEmpty() ||
            user.user_name.isEmpty() ||
            user.user_password.isEmpty() ||
            user.user_salt.isEmpty() ||
            user.full_name.isEmpty()
        ) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.EmptyField.message,
                data = UserResponseWithToken()
            )
        }

        if (userRepository.checkIfUserExistByName(username = user.user_name)) {
            return BaseResponse.ErrorResponse(
                message = ResponseMessages.UserAlreadyExist.message,
                data = UserResponseWithToken()
            )
        }

        val userInsert = userRepository.insertUser(user = user)

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessSignup.message, data = UserResponseWithToken(
                userID = userInsert.user_id,
                userName = userInsert.user_name,
                token = userInsert.user_name.generateToken()
            )
        )
    }
}