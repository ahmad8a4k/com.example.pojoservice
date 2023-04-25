package com.example.routes.mapper.image

import com.example.data.request.ImagePaging
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.pagingParameter(): ImagePaging {
    val userParameters = call.request.queryParameters
    return ImagePaging(
        pageSize = userParameters.getOrFail("page_size"),
        pageNum = userParameters.getOrFail("page_number"),
    )
}