package com.example.data.request

data class NaturalByCategoryDetailsAndPagingRequest(
    val pageSize :String,
    val pageNum:String,
    val category_id:Int,
    val category_name:String
)

