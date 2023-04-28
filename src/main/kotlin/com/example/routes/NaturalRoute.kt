package com.example.routes

import com.example.domain.endpoints.ImageEndPoint
import com.example.domain.usecases.natural.GetNaturalImagesByPagingUseCase
import com.example.domain.usecases.natural.GetNaturalLiteImagesByCategoryUseCase
import com.example.domain.usecases.natural.GetNaturalLiteImagesByColorUseCase
import com.example.routes.mapper.image.pagingParameter
import com.example.routes.mapper.natural.naturalByCategoriesAndPaging
import com.example.routes.mapper.natural.naturalByColorsAndPaging
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.naturalsImagesRoute() {
    val naturalsImages by inject<GetNaturalImagesByPagingUseCase>()
    val naturalByCategoryAndPaging by inject<GetNaturalLiteImagesByCategoryUseCase>()
    val naturalsByColorUseCase by inject<GetNaturalLiteImagesByColorUseCase>()

        put(ImageEndPoint.NaturalImages.path) {
            val imagePageParameters = pagingParameter()
            val naturalImages = naturalsImages(imagePageParameters.pageSize.toInt(), imagePageParameters.pageNum.toInt())
            call.respond(message = naturalImages, status = naturalImages.statuesCode)
        }

        put(ImageEndPoint.NaturalByCategories.path) {
            val naturalByCategoriesAndPagingParameters = naturalByCategoriesAndPaging()
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

}
