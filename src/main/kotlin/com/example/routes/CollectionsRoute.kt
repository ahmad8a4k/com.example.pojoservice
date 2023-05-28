package com.example.routes

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.collections.GetAdminsCollectionsUseCase
import com.example.domain.usecases.collections.GetUsersCollectionsUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.collections() {
    val listOfUsersCollectionsUseCase by inject<GetUsersCollectionsUseCase>()
    val listOfAdminsCollectionsUseCase by inject<GetAdminsCollectionsUseCase>()

    get(path = ImageEndPoint.UsersCollections.path) {
        val collections = listOfUsersCollectionsUseCase()
        call.respond(message = collections, status = collections.statuesCode)
    }

    get(path = ImageEndPoint.AdminsCollections.path) {
        val collections = listOfAdminsCollectionsUseCase()
        call.respond(message = collections, status = collections.statuesCode)
    }
}