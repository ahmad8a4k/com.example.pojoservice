package com.example.routes

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.collections.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Route.collections() {
    val listOfUsersCollectionsUseCase by inject<GetUsersCollectionsUseCase>()
    val listOfAdminsCollectionsUseCase by inject<GetAdminsCollectionsUseCase>()
    val imagesFromUsersCollectionsUseCase by inject<GetImagesUserCollectionsUseCase>()
    val imagesFromAdminsCollectionsUseCase by inject<GetImagesAdminsCollectionsUseCase>()
    val limitAdminsCollectionsUseCase by inject<GetLimitAdminsCollectionsUseCase>()
    val userCollectionDetailsUseCase by inject<GetUserCollectionDetailsUseCase>()
    val adminCollectionDetailsUseCase by inject<GetAdminCollectionDetailsUseCase>()

    get(path = ImageEndPoint.UsersCollections.path) {
        val collections = listOfUsersCollectionsUseCase()
        call.respond(message = collections, status = collections.statuesCode)
    }

    get(path = ImageEndPoint.AdminsCollections.path) {
        val collections = listOfAdminsCollectionsUseCase()
        call.respond(message = collections, status = collections.statuesCode)
    }

    put(path = ImageEndPoint.ImagesByUsersCollections.path) {
        val collectionId = call.request.queryParameters.getOrFail("collection_id")
        val userId = call.request.queryParameters.getOrFail("user_id").toInt()
        val images = imagesFromUsersCollectionsUseCase(collectionId = collectionId.toInt(), userId = userId )
        call.respond(message = images, status = images.statuesCode)
    }

    put(path = ImageEndPoint.ImagesByAdminsCollections.path) {
        val collectionId = call.request.queryParameters.getOrFail("collection_id")
        val userId = call.request.queryParameters.getOrFail("user_id").toInt()
        val images = imagesFromAdminsCollectionsUseCase(collectionId = collectionId.toInt(), userId = userId)
        call.respond(message = images, status = images.statuesCode)
    }

    put(path = ImageEndPoint.LimitAdminsCollections.path) {
        val limit = call.request.queryParameters.getOrFail("limit")
        val collections = limitAdminsCollectionsUseCase(limit = limit.toInt())
        call.respond(message = collections, status = collections.statuesCode)
    }

    put(path = ImageEndPoint.UserCollectionDetails.path) {
        val collectionId = call.request.queryParameters.getOrFail("collection_id")
        val collection = userCollectionDetailsUseCase(collectionId = collectionId.toInt())
        call.respond(message = collection, status = collection.statuesCode)
    }

    put(path = ImageEndPoint.AdminCollectionDetails.path) {
        val collectionId = call.request.queryParameters.getOrFail("collection_id")
        val collection = adminCollectionDetailsUseCase(collectionId = collectionId.toInt())
        call.respond(message = collection, status = collection.statuesCode)
    }
}