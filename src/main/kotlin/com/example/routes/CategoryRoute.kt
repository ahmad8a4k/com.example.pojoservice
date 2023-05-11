package com.example.routes

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.category.GetAllLiteCategoriesUserCase
import com.example.domain.usecases.category.GetSevenCategoriesUseCases
import com.example.domain.usecases.category.UpdateAllBlurHashCategory
import com.example.domain.usecases.natural.GetAllNaturalCategoriesUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.categoriesRoute() {

    val getSevenCategoriesUseCases by inject<GetSevenCategoriesUseCases>()
    val getAllCategoriesUseCase by inject<GetAllCategoriesUseCase>()
    val naturalCategories by inject<GetAllNaturalCategoriesUseCase>()
    val getAllLiteCategories by inject<GetAllLiteCategoriesUserCase>()
    val updateBlurHashForALlCategories by inject<UpdateAllBlurHashCategory>()

    /**
    Images Category
     */
    get(ImageEndPoint.SevenCategories.path) {
        val categories = getSevenCategoriesUseCases()
        call.respond(message = categories, status = categories.statuesCode)
    }

    get(ImageEndPoint.AllCategories.path) {
        val categories = getAllCategoriesUseCase()
        call.respond(message = categories, status = categories.statuesCode)
    }

    get(ImageEndPoint.AllLiteCategories.path) {
        val categories = getAllLiteCategories()
        call.respond(message = categories, status = categories.statuesCode)
    }

    get(ImageEndPoint.EncodeBlurHashCategories.path) {
        updateBlurHashForALlCategories()
    }

    /**
    Natural Category
     */
    get(ImageEndPoint.AllNaturalCategories.path) {
        val naturalCategory = naturalCategories()
        call.respond(message = naturalCategory, status = naturalCategory.statuesCode)
    }
}