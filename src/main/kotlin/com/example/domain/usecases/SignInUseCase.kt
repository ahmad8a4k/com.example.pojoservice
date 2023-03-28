package com.example.domain.usecases

import com.example.data.repositories.userRepository.UserRepository
import com.example.data.response.UserTokenResponse
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateToken
import com.example.utils.verifyPassword

class SignInUseCase constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(userName : String, userPassword: String): BaseResponse<UserTokenResponse>{
        val userDto = userRepository.getUserByUserName(username = userName)

        if(userName.isEmpty()){
            return BaseResponse.ErrorResponse(message = ResponseMessages.EmptyField.message) as
                    BaseResponse<UserTokenResponse>
        }

        if(userDto == null){
            return BaseResponse.ErrorResponse(message = ResponseMessages.NotFoundUser.message) as
                    BaseResponse<UserTokenResponse>
        }

        val validationPassword = SaltedHash(hash = userDto.userPassword, salt = userDto.userSalt).verifyPassword(userPassword)

        if(!validationPassword){
           return BaseResponse.ErrorResponse(message = ResponseMessages.IncorrectPassword.message) as
                   BaseResponse<UserTokenResponse>
        }

        return BaseResponse.SuccessResponse(data = UserTokenResponse(token = userDto.userName.generateToken()))
    }
}