package com.example.data.request

data class NaturalByColorAndPagingRequest(
    val pageSize :String,
    val pageNum:String,
    val color_id:Int,
    val color_name:String
)
