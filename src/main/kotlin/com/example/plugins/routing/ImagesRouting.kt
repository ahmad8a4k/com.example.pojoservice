package com.example.plugins.routing

import com.example.routes.categoriesRoute
import com.example.routes.images
import com.example.routes.naturalsImagesRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.imageConfigRouting() {
    routing {
        /**
         * Images
         */
        route("pojo_images") {
            images()
        }
        /**
         * Categories
         */
        route("pojo_category") {
            categoriesRoute()
        }
        /**
         * Naturals
         */
        route("pojo_naturals"){
            naturalsImagesRoute()
        }
    }
}
