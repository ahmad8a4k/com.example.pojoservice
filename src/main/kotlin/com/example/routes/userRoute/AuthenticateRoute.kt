package com.example.routes.userRoute

import com.example.data.response.UserTokenResponse
import com.example.domain.endpoints.UserEndPoint
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authenticateRoute(){
    authenticate{
        get(UserEndPoint.Authenticate.path){

            call.respond(
                status = HttpStatusCode.OK,
                message = UserTokenResponse(
                    token = ""
                )
            )
        }
    }
}