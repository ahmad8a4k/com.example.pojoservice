package com.example.data.tables

import com.example.data.entities.UserCollectionEntity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserCollectionTable : Table<UserCollectionEntity>("user_collections") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val userId = int("user_id").references(UserTable) { it.userId }
    val collectionName = varchar("collection_name").bindTo { it.collectionName }
    val collectionDescription = varchar("collection_description").bindTo { it.collectionDescription }
    val collectionInvisibility = boolean("collection_invisibility").bindTo { it.collectionInvisibility }
    val collectionUrl = varchar("collection_url").bindTo { it.collectionUrl }
}