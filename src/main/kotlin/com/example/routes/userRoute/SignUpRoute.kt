package com.example.routes.userRoute

import com.example.domain.endpoints.UserEndPoint
import com.example.domain.usecases.SignUpUseCase
import com.example.routes.mapper.signupMapper.userRequestToDto
import com.example.routes.mapper.signupMapper.userParameters
import io.ktor.server.application.*
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


