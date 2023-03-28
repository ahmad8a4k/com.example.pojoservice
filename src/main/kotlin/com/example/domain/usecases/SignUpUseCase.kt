package com.example.domain.usecases

import com.example.data.dto.UserDto
import com.example.data.repositories.userRepository.UserRepository
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class SignUpUseCase constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: UserDto): BaseResponse<UserDto> {
        if (
            user.email.isEmpty() ||
            user.userName.isEmpty() ||
            user.userPassword.isEmpty() ||
            user.userSalt.isEmpty()
        ) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyField.message) as
                    BaseResponse<UserDto>
        }

        if (userRepository.checkIfUserExistByName(username = user.userName)) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.UserAlreadyExist.message)
                    as BaseResponse<UserDto>
        }

        userRepository.insertUser(user = user)

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessSignup.message, data = true) as
                BaseResponse<UserDto>
    }
}