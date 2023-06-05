package com.example.data.dto.collections

import kotlinx.serialization.SerialName

data class CollectionWithUserDto(
    @SerialName("collection_id") val collection_id: Int,
    @SerialName("collection_name") val collection_name: String,
    @SerialName("collection_description") val collection_description: String,
    @SerialName("collection_invisibility") val collection_invisibility: Boolean,
    @SerialName("likesCount") val likesCount: Int,
    @SerialName("collection_url") val collection_url: String,
    @SerialName("user_id") val user_id: Int,
    @SerialName("user_name") val user_name: String,
    @SerialName("user_url") val user_url: String
)

data class CollectionDto(
    @SerialName("collection_id") val collection_id: Int = 0,
    @SerialName("collection_name") val collection_name: String,
    @SerialName("collection_description") val collection_description: String,
    @SerialName("collection_invisibility") val collection_invisibility: Boolean,
    @SerialName("likesCount") val likesCount: Int,
    @SerialName("collection_url") val collection_url: String
)