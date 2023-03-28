package com.example.routes.mapper.signupMapper

import com.example.data.request.UserRegisterRequest
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.userParameters(): UserRegisterRequest {
    val userParameters = call.request.queryParameters
    return UserRegisterRequest(
        userEmail = userParameters.getOrFail("email"),
        username = userParameters.getOrFail("name"),
        password = userParameters.getOrFail("password")
    )
}