package com.example.data.request

data class NaturalByColorAndPagingRequest(
    val pageSize :Int,
    val pageNum:Int,
    val color_id:Int,
    val color_name:String
)
