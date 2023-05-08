package com.example.data.tables

import com.example.data.entities.Tags
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object TagsTable : Table<Tags>("tags") {
    var id = int("id").bindTo { it.id }.primaryKey()
    var tag_name = varchar("tag_name").bindTo { it.tagName }
}