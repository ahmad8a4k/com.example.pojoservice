package com.example.routes

import com.example.domain.endpoints.UserEndPoint
import com.example.domain.usecases.user.*
import com.example.domain.usecases.util.isValidEmail
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
    val userSignInByUserNameUseCase by inject<UserSignInByUserNameUseCase>()
    val userSignInByUserEmailUseCase by inject<UserSignInByUserEmailUserCase>()
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
        if(signInParameters.usernameOrEmail.isValidEmail()) {
            val signInToken = userSignInByUserEmailUseCase(
                signInParameters.usernameOrEmail,
                signInParameters.password
            )
            call.respond(message = signInToken, status = signInToken.statuesCode)
        }else{
            val signInToken = userSignInByUserNameUseCase(
                signInParameters.usernameOrEmail, signInParameters.password
            )
            call.respond(message = signInToken, status = signInToken.statuesCode)
        }
    }

    authenticate {
        get(UserEndPoint.Authenticate.path) {
            val auth = authUserUserCase()
            call.respond(message = auth, status = auth.statuesCode)
        }
    }

    get(UserEndPoint.DeleteUserByUsernameAndPassword.path) {
        val signInParameters = userNameAndPasswordRequest()
        val signInToken = deleteUserUseCase(signInParameters.usernameOrEmail, signInParameters.password)
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