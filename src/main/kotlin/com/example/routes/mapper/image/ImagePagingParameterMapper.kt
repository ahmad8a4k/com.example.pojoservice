package com.example.routes.mapper.image

import com.example.data.request.ImageCategoryColorRequest
import com.example.data.request.ImagePaging
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.pagingParameter(): ImagePaging {
    val callParameters = call.request.queryParameters
    return ImagePaging(
        pageSize = callParameters.getOrFail("page_size"),
        pageNum = callParameters.getOrFail("page_number"),
        userId = callParameters.getOrFail("user_id").toInt()
    )
}

fun PipelineContext<*, ApplicationCall>.imageCategoryColorParameters(): ImageCategoryColorRequest {
    val callParameters = call.request.queryParameters
    return ImageCategoryColorRequest(
        imageId = callParameters.getOrFail("image_id").toInt(),
        categoryId = callParameters.getOrFail("category_id").toInt(),
        colorId = callParameters.getOrFail("color_id").toInt(),
        userId = callParameters.getOrFail("user_id").toInt()
    )
}