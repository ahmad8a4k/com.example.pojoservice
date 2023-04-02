package com.example.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.token.TokenConfig
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureSecurity(config: TokenConfig) {

    authentication {
        jwt {
            realm = "PojoRealm"
            verifier(
                JWT
                    .require(Algorithm.HMAC256(config.secret))
                    .withAudience(config.audience)
                    .withIssuer(config.issuer)
                    .build()
            )
            challenge { defaultScheme, realm ->
                val tokenErrorResponse = BaseResponse.ErrorResponse(
                    message = ResponseMessages.FailAuthentication.message,
                    data = "Fails Token"
                )
                call.respond(message = tokenErrorResponse, status = tokenErrorResponse.statusCode)
            }
            validate { credential ->
                if (credential.payload.audience.contains(config.audience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }
}
