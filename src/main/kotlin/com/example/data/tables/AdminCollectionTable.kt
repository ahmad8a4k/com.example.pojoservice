package com.example.data.tables

import com.example.data.entities.AdminCollectionEntity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AdminCollectionTable : Table<AdminCollectionEntity>("admin_collections") {
    val id = int("id").bindTo { it.aId }.primaryKey()
    val adminId = int("admin_id").references(AdminsTable) { it.aAdminId }
    val collectionName = varchar("collection_name").bindTo { it.aCollectionName }
    val collectionDescription = varchar("collection_description").bindTo { it.aCollectionDescription }
    val collectionInvisibility = boolean("collection_invisibility").bindTo { it.aCollectionInvisibility }
    val collectionUrl = varchar("collection_url").bindTo { it.collectionUrl }
}