package com.example.domain.usecases.user

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
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyField.message, data = false) as
                    BaseResponse<UpdateUserPasswordUseCase>
        }

        if(userDto == null){
            return BaseResponse.ErrorResponse(message = ResponseMessages.NotFoundUser.message, data = false) as
                    BaseResponse<UpdateUserPasswordUseCase>
        }

        val validationPassword = SaltedHash(hash = userDto.user_password, salt = userDto.user_salt)
            .verifyPassword(parameters.password)

        if(!validationPassword){
            return BaseResponse.ErrorResponse(message = ResponseMessages.IncorrectPassword.message, data = false) as
                    BaseResponse<UpdateUserPasswordUseCase>
        }

        val saltedHash = parameters.newPassword.generateSaltedHash()
        userRepository.updateUserPassword(user = userDto.copy(user_password = saltedHash.hash
            , user_salt = saltedHash.salt))

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessUpdatePassword.message, data = true
        ) as BaseResponse<UpdateUserPasswordUseCase>
    }


}