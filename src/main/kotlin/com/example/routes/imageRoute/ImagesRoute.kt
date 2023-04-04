package com.example.routes.imageRoute

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.image.*
import com.example.routes.mapper.image.imagesPagingParameter
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import org.koin.ktor.ext.inject

fun Route.imagesPagingRoute() {
    val imagesUseCase by inject<ImagesByPageSizeUseCase>()
    put(ImageEndPoint.Images.path) {
        val imagePageParameters = imagesPagingParameter()
        val images = imagesUseCase(imagePageParameters.pageSize.toInt(), imagePageParameters.pageNum.toInt())
        call.respond(message = images, status = images.statuesCode)
    }
}

fun Route.fifteenImagesRoute() {
    val imagesUseCase by inject<GetFifteenImagesDetailsUseCase>()
    get(ImageEndPoint.FifteenImages.path) {
        val images = imagesUseCase()
        call.respond(message = images, status = images.statuesCode)
    }
}

fun Route.naturalsImagesRoute() {
    val naturalsImages by inject<GetNaturalImagesByPagingUseCase>()
    put(ImageEndPoint.NaturalImages.path) {
        val imagePageParameters = imagesPagingParameter()
        val naturalImages = naturalsImages(imagePageParameters.pageSize.toInt(), imagePageParameters.pageNum.toInt())
        call.respond(message = naturalImages, status = naturalImages.statuesCode)
    }
}

fun Route.naturalCategoriesRoute() {
    val naturalCategories by inject<GetAllNaturalCategoriesUseCase>()
    get(ImageEndPoint.AllNaturalCategories.path) {
        val naturalCategory = naturalCategories()
        call.respond(message = naturalCategory, status = naturalCategory.statuesCode)
    }
}