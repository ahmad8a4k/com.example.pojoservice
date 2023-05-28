package com.example.plugins.routing

import com.example.routes.categoriesRoute
import com.example.routes.collections
import com.example.routes.images
import com.example.routes.naturalsImagesRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.imageConfigRouting() {
    routing {
        /**
         * Images
         */
        route(path = "pojo_images") {
            images()
        }
        /**
         * Categories
         */
        route(path = "pojo_category") {
            categoriesRoute()
        }
        /**
         * Naturals
         */
        route(path = "pojo_naturals") {
            naturalsImagesRoute()
        }
        /**
         * Collections
         */
        route(path = "pojo_collections") {
            collections()
        }
    }
}
