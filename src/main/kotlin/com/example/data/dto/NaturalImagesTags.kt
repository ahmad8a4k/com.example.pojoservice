package com.example.data.dto

import com.example.data.dto.imageDetails.LiteNaturalDetailsDto
import kotlinx.serialization.SerialName

data class NaturalImagesTags(
    @SerialName("natural_image") val natural_Image: LiteNaturalDetailsDto,
    @SerialName("natural_tag") val natural_tag: NaturalTagDto,
)
