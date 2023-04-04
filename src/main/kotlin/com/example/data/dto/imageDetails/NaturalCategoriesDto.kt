package com.example.data.dto.imageDetails

import com.google.gson.annotations.SerializedName

data class NaturalCategoriesDto(
    @SerializedName("category_id") val category_id: Int? = 0,
    @SerializedName("natural_category_name") val natural_category_name: String? = "",
    @SerializedName("natural_category_url") val natural_category_url: String? = "",
    @SerializedName("natural_category_register") val natural_category_register: String = ""
)