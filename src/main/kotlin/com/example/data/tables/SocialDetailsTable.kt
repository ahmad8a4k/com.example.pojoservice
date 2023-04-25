package com.example.data.tables

import com.example.data.entities.SocialDetailsEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object SocialDetailsTable  : Table<SocialDetailsEntity>("social_details") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val social_name = varchar("social_name").bindTo { it.socialName }
}