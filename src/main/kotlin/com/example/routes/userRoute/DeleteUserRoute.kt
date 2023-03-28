package com.example.routes.userRoute

import com.example.domain.endpoints.UserEndPoint
import com.example.domain.usecases.DeleteUserUseCase
import com.example.routes.mapper.signInMapper.userNameAndPasswordRequest
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.deleteUserRoute() {
    val userUserCase by inject<DeleteUserUseCase>()
    get(UserEndPoint.DeleteUserByUsernameAndPassword.path) {
        val signInParameters = userNameAndPasswordRequest()
        val signInToken = userUserCase(signInParameters.username, signInParameters.password)
        call.respond(message = signInToken, status = signInToken.statuesCode)
    }
}