package com.example.routes

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.natural.GetAllNaturalLiteImages
import com.example.domain.usecases.natural.GetNaturalImagesByPagingUseCase
import com.example.domain.usecases.natural.GetNaturalLiteImagesByCategoryUseCase
import com.example.domain.usecases.natural.GetNaturalLiteImagesByColorUseCase
import com.example.routes.mapper.image.pagingParameter
import com.example.routes.mapper.natural.imagesByCategoryParameters
import com.example.routes.mapper.natural.naturalByColorsAndPaging
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.naturalsImagesRoute() {

    val naturalsImages by inject<GetNaturalImagesByPagingUseCase>()
    val naturalByCategoryAndPaging by inject<GetNaturalLiteImagesByCategoryUseCase>()
    val naturalsByColorUseCase by inject<GetNaturalLiteImagesByColorUseCase>()
    val allNaturalLiteUseCase by inject<GetAllNaturalLiteImages>()

    put(ImageEndPoint.NaturalImages.path) {
        val imagePageParameters = pagingParameter()
        val naturalImages = naturalsImages(
            pageSize = imagePageParameters.pageSize.toInt(),
            pageNumber = imagePageParameters.pageNum.toInt()
        )
        call.respond(message = naturalImages, status = naturalImages.statuesCode)
    }

    put(ImageEndPoint.NaturalByCategories.path) {
        val naturalByCategoriesAndPagingParameters = imagesByCategoryParameters()
        val naturalImages = naturalByCategoryAndPaging(
            pageSize = naturalByCategoriesAndPagingParameters.pageSize.toInt(),
            pageNumber = naturalByCategoriesAndPagingParameters.pageNum.toInt(),
            categoryId = naturalByCategoriesAndPagingParameters.category_id,
            categoryName = naturalByCategoriesAndPagingParameters.category_name
        )
        call.respond(message = naturalImages, status = naturalImages.statuesCode)
    }

    put(ImageEndPoint.NaturalColors.path) {
        val naturalByCategoriesAndPagingParameters = naturalByColorsAndPaging()
        val naturalImages = naturalsByColorUseCase(
            pageSize = naturalByCategoriesAndPagingParameters.pageSize,
            pageNumber = naturalByCategoriesAndPagingParameters.pageNum,
            colorId = naturalByCategoriesAndPagingParameters.color_id,
            colorName = naturalByCategoriesAndPagingParameters.color_name
        )
        call.respond(message = naturalImages, status = naturalImages.statuesCode)
    }

    put(ImageEndPoint.AllLiteNatural.path) {
        val imagePageParameters = pagingParameter()
        val naturalImages = allNaturalLiteUseCase(
            pageSize = imagePageParameters.pageSize.toInt(),
            pageNumber = imagePageParameters.pageNum.toInt()
        )
        call.respond(message = naturalImages, status = naturalImages.statuesCode)
    }

}
