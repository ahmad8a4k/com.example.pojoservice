package com.example.data.tables

import com.example.data.entities.UserSocialEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserSocialTable : Table<UserSocialEntity>("users_social") {
    val user_id = int("user_id").references(UserTable)
    { it.userEntity }
    val image_details_id = int("image_details_id").references(ImageDetailsTable)
    { it.imageDetailsEntity }
    val social_details_id = int("social_details_id").references(SocialDetailsTable)
    { it.socialDetailsEntity }
}