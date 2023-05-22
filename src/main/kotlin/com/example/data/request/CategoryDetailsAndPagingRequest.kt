package com.example.data.request

data class CategoryDetailsAndPagingRequest(
    val pageSize :String,
    val pageNum:String,
    val category_id:Int,
    val category_name:String
)

