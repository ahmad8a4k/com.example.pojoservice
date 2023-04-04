package com.example.plugins.routing

import com.example.routes.imageRoute.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.imageConfigRouting() {
    routing {

        /**
         * Images
         */
        imagesPagingRoute()
        fifteenImagesRoute()

        liteImagesPagingRoute()
        /**
         * Categories
         */
        getSevenImageCategoriesRoute()
        getAllImagesCategories()

        /**
         * Naturals
         */
        naturalsImagesRoute()
        naturalCategoriesRoute()
    }
}
