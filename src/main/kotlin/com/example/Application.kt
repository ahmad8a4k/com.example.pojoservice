package com.example

import com.example.db.mainModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.plugins.routing.imageConfigRouting
import com.example.plugins.routing.userConfigRouting
import com.example.token.TokenConfig
import com.example.utils.Constants.AUDIENCE
import com.example.utils.Constants.ISSUER
import io.ktor.http.*
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {

    install(Koin) {
        modules(mainModule)
    }

    val tokenConfig = TokenConfig(
        issuer = ISSUER,
        audience = AUDIENCE,
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET") ?: "jwtsecret"
    )

    configureSecurity(tokenConfig)
    userConfigRouting()
    imageConfigRouting()
    configureSerialization()
    configureMonitoring()
}