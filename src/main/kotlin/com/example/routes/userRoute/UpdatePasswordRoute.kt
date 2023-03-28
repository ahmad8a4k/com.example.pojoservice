package com.example.routes.userRoute

import com.example.domain.endpoints.UserEndPoint
import com.example.domain.usecases.UpdateUserPasswordUseCase
import com.example.routes.mapper.signupMapper.userParameters
import com.example.routes.mapper.signupMapper.userRequestToDto
import com.example.routes.mapper.updatePasswordMapper.userUpdatePasswordParameters
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.updatePasswordRoute() {
    val userUseCase by inject<UpdateUserPasswordUseCase>()
    get(UserEndPoint.UpdatePassword.path) {
        val userParameters = userUpdatePasswordParameters()
        val updateUserPassword = userUseCase(userParameters)
        call.respond(message = updateUserPassword, status = updateUserPassword.statuesCode)
    }
}