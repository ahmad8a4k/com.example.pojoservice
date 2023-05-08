package com.example.data.tables

import com.example.data.entities.ImageUserLikes
import org.ktorm.schema.Table
import org.ktorm.schema.int

object ImageUserLikesTable : Table<ImageUserLikes>("images_user_likes") {
    var user_id = int("user_id").references(UserTable) { it.userId }
    var image_id = int("image_id").references(ImageDetailsTable) { it.imageId }
}