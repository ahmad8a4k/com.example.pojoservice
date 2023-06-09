package com.example.routes.mapper.natural

import com.example.data.request.CategoryDetailsAndPagingRequest
import com.example.data.request.NaturalByColorAndPagingRequest
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.imagesByCategoryParameters(): CategoryDetailsAndPagingRequest {
    val queryParameter = call.request.queryParameters
    return CategoryDetailsAndPagingRequest(
        pageSize = queryParameter.getOrFail("page_size"),
        pageNum = queryParameter.getOrFail("page_number"),
        category_id = queryParameter.getOrFail("category_id").toInt(),
        category_name = queryParameter.getOrFail("category_name"),
        userId = queryParameter.getOrFail("user_id").toInt()
    )
}

fun PipelineContext<*, ApplicationCall>.naturalByColorsAndPaging(): NaturalByColorAndPagingRequest {
    val queryParameter = call.request.queryParameters
    return NaturalByColorAndPagingRequest(
        pageSize = queryParameter.getOrFail("page_size").toInt(),
        pageNum = queryParameter.getOrFail("page_number").toInt(),
        color_id = queryParameter.getOrFail("color_id").toInt(),
        color_name = queryParameter.getOrFail("color_name")
    )
}