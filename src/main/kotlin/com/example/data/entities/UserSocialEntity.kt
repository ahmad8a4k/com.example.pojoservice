package com.example.data.entities

import org.ktorm.entity.Entity

interface UserSocialEntity : Entity<UserSocialEntity> {
    companion object : Entity.Factory<UserSocialEntity>()

    var userEntity: UserEntity
    var imageDetailsEntity: ImageDetails
    var socialDetailsEntity: SocialDetailsEntity
}