package com.example.routes.imageRoute

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.image.GetAllCategoriesUseCase
import com.example.domain.usecases.image.GetSevenCategoriesUseCases
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getSevenImageCategoriesRoute(){
    val categoriesUseCases by inject<GetSevenCategoriesUseCases>()
    get(ImageEndPoint.SevenCategories.path) {
        val categories = categoriesUseCases()
        call.respond(message = categories, status = categories.statuesCode)
    }
}

fun Route.getAllImagesCategories(){
    val categoriesUseCases by inject<GetAllCategoriesUseCase>()
    get(ImageEndPoint.AllCategories.path) {
        val categories = categoriesUseCases()
        call.respond(message = categories, status = categories.statuesCode)
    }
}

