package com.example.plugins.routing

import com.example.routes.imageRoute.fifteenImagesRoute
import com.example.routes.imageRoute.getAllImagesCategories
import com.example.routes.imageRoute.getSevenImageCategoriesRoute
import com.example.routes.imageRoute.imagesPagingRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.imageConfigRouting() {
    routing {

        /**
         * Images
         */
        imagesPagingRoute()
        fifteenImagesRoute()

        /**
         * Categories
         */
        getSevenImageCategoriesRoute()
        getAllImagesCategories()

    }
}
