package com.example.domain.endpoints

sealed class ImageEndPoint(val path: String) {
    /**
     * Images
     */
    object Images : ImageEndPoint(path = "image/images")
    object LiteImages : ImageEndPoint(path = "image/lite_images")
    object FifteenImages : ImageEndPoint(path = "image/fifteen_top_images")
    object Details : ImageEndPoint(path = "image/image_details")
    object LastUpload : ImageEndPoint(path = "image/image_last_upload")
    object Categories : ImageEndPoint(path = "image/image_categories")
    object Populars : ImageEndPoint(path = "image/image_populars")
    object Like : ImageEndPoint(path = "image/image_like")
    object Share : ImageEndPoint(path = "image/image_share")
    object Download : ImageEndPoint(path = "image/image_download")

    /**
     * Categories
     */
    object SevenCategories : ImageEndPoint(path = "category/seven_category")
    object AllCategories : ImageEndPoint(path = "category/categories")

    /**
     * Natural
     */
    object AllNaturalCategories : ImageEndPoint(path = "category/natural_categories")
    object NaturalImages : ImageEndPoint(path = "image/natural_images")
}