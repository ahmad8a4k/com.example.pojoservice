package com.example.domain.endpoints

sealed class ImageEndPoint(val path: String) {

    /**
     * Images
     */
    object Images : ImageEndPoint(path = "/images")
    object LiteImages : ImageEndPoint(path = "/lite_images")
    object FifteenImages : ImageEndPoint(path = "/fifteen_top_images")
    object Details : ImageEndPoint(path = "/image_details")
    object LastUpload : ImageEndPoint(path = "/image_last_upload")
    object Categories : ImageEndPoint(path = "/image_categories")
    object Populars : ImageEndPoint(path = "/image_populars")
    object Like : ImageEndPoint(path = "/image_like")
    object Share : ImageEndPoint(path = "/image_share")
    object Download : ImageEndPoint(path = "/image_download")
    object TenTopRated : ImageEndPoint(path = "/ten_top_rated")
    object ListTenTopRated : ImageEndPoint(path = "/list_top_rated")
    object AllLiteImagesByCategory : ImageEndPoint(path = "/lite_images_by_category")

    object ImageCompleteDetails : ImageEndPoint(path = "/images_complete_details")
    object TryEncodeImages : ImageEndPoint(path = "/tryEncodeImages")

    /**
     * Categories
     */
    object SevenCategories : ImageEndPoint(path = "/seven_category")
    object AllCategories : ImageEndPoint(path = "/categories")
    object AllLiteCategories : ImageEndPoint(path = "/lite_categories")
    object EncodeBlurHashCategories : ImageEndPoint(path = "/encode_blurHash_categories")

    /**
     * Colors
     */
    object AllColors : ImageEndPoint(path = "/all_colors")

    /**
     * Natural
     */
    object AllNaturalCategories : ImageEndPoint(path = "/natural_categories")
    object NaturalImages : ImageEndPoint(path = "/natural_images")
    object NaturalByCategories : ImageEndPoint(path = "/natural_by_category")
    object NaturalColors : ImageEndPoint(path = "/natural_by_color")
    object AllLiteNatural : ImageEndPoint(path = "/all_natural_lite_images")

    /**
     * Collections
     */
    object UsersCollections: ImageEndPoint(path = "/users_collections")
    object AdminsCollections: ImageEndPoint(path = "/admins_collections")

}