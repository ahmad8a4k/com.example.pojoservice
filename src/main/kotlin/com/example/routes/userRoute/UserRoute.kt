package com.example.routes.userRoute

import com.example.domain.endpoints.UserEndPoint
import com.example.domain.usecases.user.*
import com.example.routes.mapper.updatePasswordMapper.userUpdatePasswordParameters
import com.example.routes.mapper.user.signInMapper.userNameAndPasswordRequest
import com.example.routes.mapper.user.signupMapper.userRequestToDto
import com.example.routes.mapper.user.signupMapper.userParameters
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.signUpRoute() {
    val userUseCase by inject<SignUpUseCase>()
    post(UserEndPoint.SignUp.path) {
        val userParameters = userParameters()
        val signUpUser = userUseCase(userParameters.userRequestToDto())
        call.respond(message = signUpUser, status = signUpUser.statuesCode)
    }
}

fun Route.signInRoute() {
    val userUserCase by inject<SignInUseCase>()
    post(UserEndPoint.SignIn.path) {
        val signInParameters = userNameAndPasswordRequest()
        val signInToken = userUserCase(signInParameters.username, signInParameters.password)
        call.respond(message = signInToken, status = signInToken.statuesCode)
    }
}

fun Route.authenticateRoute() {
    val authentication by inject<AuthUserUserCase>()
    authenticate {
        get(UserEndPoint.Authenticate.path) {
            val auth = authentication()
            call.respond(message = auth, status = auth.statuesCode)
        }
    }
}

fun Route.deleteUserRoute() {
    val userUserCase by inject<DeleteUserUseCase>()
    get(UserEndPoint.DeleteUserByUsernameAndPassword.path) {
        val signInParameters = userNameAndPasswordRequest()
        val signInToken = userUserCase(signInParameters.username, signInParameters.password)
        call.respond(message = signInToken, status = signInToken.statuesCode)
    }
}

fun Route.updatePasswordRoute() {
    val userUseCase by inject<UpdateUserPasswordUseCase>()
    get(UserEndPoint.UpdatePassword.path) {
        val userParameters = userUpdatePasswordParameters()
        val updateUserPassword = userUseCase(userParameters)
        call.respond(message = updateUserPassword, status = updateUserPassword.statuesCode)
    }
}