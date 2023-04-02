package com.example.routes.imageRoute

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.image.GetFifteenImagesDetailsUseCase
import com.example.domain.usecases.image.ImagesByPageSizeUseCase
import com.example.routes.mapper.image.imagesPagingParameter
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.imagesPagingRoute(){
    val imagesUseCase by inject<ImagesByPageSizeUseCase>()
    put(ImageEndPoint.Images.path) {
        val imagePageParameters = imagesPagingParameter()
        val images = imagesUseCase(imagePageParameters.pageSize.toInt(), imagePageParameters.pageNum.toInt())
        call.respond(message = images, status = images.statuesCode)
    }
}

fun Route.fifteenImagesRoute(){
    val imagesUseCase by inject<GetFifteenImagesDetailsUseCase>()
    get(ImageEndPoint.FifteenImages.path) {
        val images = imagesUseCase()
        call.respond(message = images, status = images.statuesCode)
    }
}


