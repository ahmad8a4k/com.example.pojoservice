package com.example.routes

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.image.*
import com.example.routes.mapper.image.pagingParameter
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.images() {
    val imagesUseCase by inject<ImagesByPageSizeUseCase>()
    val fifteenImagesDetailsUseCase by inject<GetFifteenImagesDetailsUseCase>()
    val liteImagesUseCase by inject<GetLiteImageDetailsUseCase>()
    val tenTopRatedImageBasedOnTopWeekOrLastWeek by inject<GetTenTopRatedImagesBasedOnTopWeekOrLastWeek>()
    val listTopRatedImages by inject<GetListOfTopRatedLiteImages>()
    val listOfColorsUseCase by inject<GetAllColorsUseCase>()

    put(ImageEndPoint.Images.path) {
        val imagePageParameters = pagingParameter()
        val images = imagesUseCase(imagePageParameters.pageSize.toInt(), imagePageParameters.pageNum.toInt())
        call.respond(message = images, status = images.statuesCode)
    }

    put(ImageEndPoint.LiteImages.path) {
        val imagePageParameters = pagingParameter()
        val images = liteImagesUseCase(imagePageParameters.pageSize.toInt(), imagePageParameters.pageNum.toInt())
        call.respond(message = images, status = images.statuesCode)
    }

    get(ImageEndPoint.FifteenImages.path) {
        val images = fifteenImagesDetailsUseCase()
        call.respond(message = images, status = images.statuesCode)
    }

    get(ImageEndPoint.TenTopRated.path) {
        val topRatedList = tenTopRatedImageBasedOnTopWeekOrLastWeek()
        call.respond(
            message = topRatedList,
            status = topRatedList.statuesCode
        )
    }

    get(ImageEndPoint.AllColors.path) {
        val listOfColorUseCase = listOfColorsUseCase()
        call.respond(
            message = listOfColorUseCase,
            status = listOfColorUseCase.statuesCode
        )
    }

    put(ImageEndPoint.ListTenTopRated.path) {
        val imagePageParameters = pagingParameter()
        val topRatedList = listTopRatedImages(
            pageSize = imagePageParameters.pageSize.toInt(),
            pageNumber = imagePageParameters.pageNum.toInt()
        )
        call.respond(
            message = topRatedList,
            status = topRatedList.statuesCode
        )
    }
}