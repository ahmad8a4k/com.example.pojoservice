package com.example.data.tables

import com.example.data.entities.ImageTags
import org.ktorm.schema.Table
import org.ktorm.schema.int

object ImagesTagsTable : Table<ImageTags>("images_tags") {

    var image_id = int("image_id_tag").references(ImageDetailsTable) { it.imageId }
    var tag_id = int("tag_id").references(TagsTable) { it.tagId }
}