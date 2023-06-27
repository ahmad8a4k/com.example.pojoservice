package com.example.plugins.routing

import com.example.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.userConfigRouting() {
    routing {
        route("user") {
            userRoute()
        }
    }
}