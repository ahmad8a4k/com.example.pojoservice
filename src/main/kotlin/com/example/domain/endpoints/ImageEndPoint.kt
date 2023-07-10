package com.example.domain.endpoints

sealed class ImageEndPoint(val path: String) {

    /**
     * Images
     */
    object ThreeWeeksAgoTopRatedImages : ImageEndPoint(path = "/top_rated_recently")
    object ListTenTopRated : ImageEndPoint(path = "/list_top_rated")
    object AllLiteImagesByCategory : ImageEndPoint(path = "/lite_images_by_category")
    object ImageCompleteDetails : ImageEndPoint(path = "/images_complete_details")
    object TryEncodeImages : ImageEndPoint(path = "/tryEncodeImages")
    object LiteImagesByDetails: ImageEndPoint(path = "/images_by_date")

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

    object ImagesByUsersCollections : ImageEndPoint(path = "/images_users_collections")
    object ImagesByAdminsCollections : ImageEndPoint(path = "/images_admins_collections")
    object LimitAdminsCollections: ImageEndPoint(path = "/limit_admins_collections")
    object UserCollectionDetails : ImageEndPoint(path = "/user_collection_details")
    object AdminCollectionDetails: ImageEndPoint(path = "/admin_collection_details")

}