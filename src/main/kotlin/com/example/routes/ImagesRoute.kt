package com.example.routes

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.image.*
import com.example.routes.mapper.image.imageCategoryColorParameters
import com.example.routes.mapper.image.pagingParameter
import com.example.routes.mapper.natural.imagesByCategoryParameters
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Route.images() {

    val getTopRatedLiteImagesThreeWeeksAgoUseCase by inject<GetTopRatedLiteImagesThreeWeeksAgoUseCase>()
    val listTopRatedImages by inject<GetListOfTopRatedLiteImages>()
    val listLiteImagesByCategoryUseCase by inject<GetAllLiteImagesByCategory>()
    val listLiteImagesByCategoryUseCase2 by inject<UpdateBlurHashForLiteImagesByCategoryId>()
    val listOfImagesDetailsUseCase by inject<GetImagesDetailsBasedOnCategoryOrColorUseCase>()
    val listOfLiteImagesDetailsByDateUseCase by inject<GetLiteImagesOrderByDateUseCase>()
    val updateImageUseCase by inject<UpdateUserLikedImage>()
    val updateWatchImageCount by inject<UpdateWatchImageCountUseCase>()
    val getSearchImageByTextUseCase by inject<GetSearchImageByTextUseCase>()

    get(ImageEndPoint.ThreeWeeksAgoTopRatedImages.path) {
        val limit = call.request.queryParameters.getOrFail("limit")
        val userId = call.request.queryParameters.getOrFail("user_id").toInt()
        val topRatedList = getTopRatedLiteImagesThreeWeeksAgoUseCase(
            limit = limit.toInt(),
            userId = userId
        )
        call.respond(
            message = topRatedList,
            status = topRatedList.statuesCode
        )
    }

    put(ImageEndPoint.ListTenTopRated.path) {
        val parameters = pagingParameter()
        val topRatedList = listTopRatedImages(
            pageSize = parameters.pageSize.toInt(),
            pageNumber = parameters.pageNum.toInt(),
            userId = parameters.userId
        )
        call.respond(
            message = topRatedList,
            status = topRatedList.statuesCode
        )
    }

    put(ImageEndPoint.AllLiteImagesByCategory.path) {
        val parameters = imagesByCategoryParameters()
        val images = listLiteImagesByCategoryUseCase(
            pageSize = parameters.pageSize.toInt(),
            page = parameters.pageNum.toInt(),
            categoryId = parameters.category_id,
            categoryName = parameters.category_name,
            userId = parameters.userId
        )
        call.respond(
            message = images,
            status = images.statuesCode
        )
    }

    get(ImageEndPoint.ImageCompleteDetails.path) {
        val parameters = imageCategoryColorParameters()
        val imagesDetails = listOfImagesDetailsUseCase(
            imageId = parameters.imageId,
            categoryId = parameters.categoryId,
            colorId = parameters.colorId,
            userId = parameters.userId
        )
        call.respond(message = imagesDetails, status = imagesDetails.statuesCode)
    }

    get(ImageEndPoint.TryEncodeImages.path) {
        listLiteImagesByCategoryUseCase2()
    }

    put(ImageEndPoint.LiteImagesByDetails.path) {
        val parameters = pagingParameter()
        val images = listOfLiteImagesDetailsByDateUseCase(
            pageSize = parameters.pageSize.toInt(),
            pageNumber = parameters.pageNum.toInt(),
            userId = parameters.userId
        )
        call.respond(message = images, status = images.statuesCode)
    }

    put(ImageEndPoint.UpdateUserLikedImage.path) {
        val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
        val userId = call.request.queryParameters.getOrFail("user_id").toInt()
        val updateImages = updateImageUseCase(userId = userId, imageId = imageId)
        call.respond(message = updateImages, status = updateImages.statuesCode)
    }

    put(ImageEndPoint.UpdateWatchImage.path) {
        val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
        val updateWatch = updateWatchImageCount(imageId = imageId)
        call.respond(message = updateWatch, status = updateWatch.statuesCode)
    }

    put(ImageEndPoint.SearchImageByText.path) {
        val imageText = call.request.queryParameters.getOrFail("image_text")
        val userId = call.request.queryParameters.getOrFail("user_id").toInt()
        val searchResult = getSearchImageByTextUseCase(imageTitle = imageText, userId = userId)
        call.respond(message = searchResult, status = searchResult.statuesCode)
    }

}