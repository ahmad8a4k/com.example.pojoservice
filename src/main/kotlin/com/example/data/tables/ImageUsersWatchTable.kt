package com.example.data.tables

import com.example.data.entities.ImageUsersWatchEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object ImageUsersWatchTable : Table<ImageUsersWatchEntity>("images_users_watch") {
    var user_id = int("user_id").references(UserTable) { it.userId }
    var image_id = int("image_id").references(ImageDetailsTable) { it.imageId }
}