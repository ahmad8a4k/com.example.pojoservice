package com.example.data.tables

import com.example.data.entities.UserAdminCollectionLikeEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UserAdminCollectionLikeTable : Table<UserAdminCollectionLikeEntity>("user_admin_collection_like") {
    var user_id = int("user_id").references(UserTable) { it.userId }
    var collection_id = int("collection_id").references(AdminCollectionTable) { it.collectionId }
}