package com.example.utils

import com.example.data.response.MessageResponse
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

@JsonSerialize
sealed class BaseResponse<T>(@JsonIgnore open val statuesCode: HttpStatusCode) {

    @JsonSerialize
    data class SuccessResponse<T>(
        val status: Boolean = true,
        val data: T? = null,
        val message: String = "",
        val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class SuccessListResponse<T>(
        val status: Boolean = true,
        val data: List<T>? = emptyList(),
        val message: String = "",
        val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class ErrorResponse(
        val status: Boolean = false,
        val message: String,
        val statusCode: HttpStatusCode = HttpStatusCode.Conflict,
    ) : BaseResponse<Any>(statusCode)

    @JsonSerialize
    data class SuccessSignResponse<T>(
        val status: Boolean = true,
        val data: T?,
        val message: String = ResponseMessages.SuccessSignIn.message,
        val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)
}

suspend fun ApplicationCall.notFoundResponse() {
    return respond(
        status = HttpStatusCode.NotFound,
        MessageResponse(false, "")
    )
}