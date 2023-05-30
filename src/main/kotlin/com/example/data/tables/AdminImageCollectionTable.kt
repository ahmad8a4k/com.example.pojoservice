package com.example.data.tables

import com.example.data.entities.AdminImageCollectionEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object AdminImageCollectionTable : Table<AdminImageCollectionEntity>("admin_image_collection") {
    var collection_id = int("collection_id").references(AdminCollectionTable) { it.adminCollectionId }
    var image_id = int("image_id").references(ImageDetailsTable) { it.aImageDetailsEntity }
}