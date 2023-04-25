package com.example.routes

import com.example.domain.endpoints.UserEndPoint
import com.example.domain.usecases.user.*
import com.example.routes.mapper.user.updatePasswordMapper.userIdParameters
import com.example.routes.mapper.user.updatePasswordMapper.userUpdatePasswordParameters
import com.example.routes.mapper.user.signInMapper.userNameAndPasswordRequest
import com.example.routes.mapper.user.signupMapper.userRequestToDto
import com.example.routes.mapper.user.signupMapper.userParameters
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.userRoute(){
    val signUpUseCase by inject<SignUpUseCase>()
    val signInUseCase by inject<SignInUseCase>()
    val authUserUserCase by inject<AuthUserUserCase>()
    val updateUserPasswordUseCase by inject<UpdateUserPasswordUseCase>()
    val deleteUserUseCase by inject<DeleteUserUseCase>()
    val getUserDetailsByIDUserCase by inject<GetUserDetailsByIDUserCase>()

    post(UserEndPoint.SignUp.path) {
        val userParameters = userParameters()
        val signUpUser = signUpUseCase(userParameters.userRequestToDto())
        call.respond(message = signUpUser, status = signUpUser.statuesCode)
    }

    post(UserEndPoint.SignIn.path) {
        val signInParameters = userNameAndPasswordRequest()
        val signInToken = signInUseCase(signInParameters.username, signInParameters.password)
        call.respond(message = signInToken, status = signInToken.statuesCode)
    }

    authenticate {
        get(UserEndPoint.Authenticate.path) {
            val auth = authUserUserCase()
            call.respond(message = auth, status = auth.statuesCode)
        }
    }

    get(UserEndPoint.DeleteUserByUsernameAndPassword.path) {
        val signInParameters = userNameAndPasswordRequest()
        val signInToken = deleteUserUseCase(signInParameters.username, signInParameters.password)
        call.respond(message = signInToken, status = signInToken.statuesCode)
    }

    get(UserEndPoint.UpdatePassword.path) {
        val userParameters = userUpdatePasswordParameters()
        val updateUserPassword = updateUserPasswordUseCase(userParameters)
        call.respond(message = updateUserPassword, status = updateUserPassword.statuesCode)
    }

    put(UserEndPoint.UserDetailsByUserID.path) {
        val userParameters = userIdParameters()
        val userDetails = getUserDetailsByIDUserCase(userParameters)
        call.respond(message = userDetails, status = userDetails.statuesCode)
    }
}