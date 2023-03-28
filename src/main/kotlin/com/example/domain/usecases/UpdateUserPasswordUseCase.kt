package com.example.domain.usecases

import com.example.data.dto.UserDto
import com.example.data.repositories.userRepository.UserRepository
import com.example.data.request.UpdateUserPasswordRequest
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateSaltedHash
import com.example.utils.verifyPassword

class UpdateUserPasswordUseCase constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(parameters : UpdateUserPasswordRequest): BaseResponse<UpdateUserPasswordUseCase> {
        val userDto = userRepository.getUserByUserName(username = parameters.username)

        if (
            parameters.password.isEmpty() ||
            parameters.newPassword.isEmpty()
        ) {
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyField.message) as
                    BaseResponse<UpdateUserPasswordUseCase>
        }

        if(userDto == null){
            return BaseResponse.ErrorResponse(message = ResponseMessages.NotFoundUser.message) as
                    BaseResponse<UpdateUserPasswordUseCase>
        }

        val validationPassword = SaltedHash(hash = userDto.userPassword, salt = userDto.userSalt)
            .verifyPassword(parameters.password)

        if(!validationPassword){
            return BaseResponse.ErrorResponse(message = ResponseMessages.IncorrectPassword.message) as
                    BaseResponse<UpdateUserPasswordUseCase>
        }

        val saltedHash = parameters.newPassword.generateSaltedHash()
        userRepository.updateUserPassword(user = userDto.copy(userPassword = saltedHash.hash
            , userSalt = saltedHash.salt))

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessUpdatePassword.message, data = true
        ) as BaseResponse<UpdateUserPasswordUseCase>
    }


}