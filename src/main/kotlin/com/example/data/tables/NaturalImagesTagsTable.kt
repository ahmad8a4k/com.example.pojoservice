package com.example.data.tables

import com.example.data.entities.NaturalImagesTag
import org.ktorm.schema.Table
import org.ktorm.schema.int

object NaturalImagesTagsTable : Table<NaturalImagesTag>("natural_image_tags") {
    var natural_id = int("natural_id").references(NaturalTable) { it.naturalImages }
    var natural_tag_id = int("natural_tag_id").references(NaturalTagTable) { it.naturalTag }
}