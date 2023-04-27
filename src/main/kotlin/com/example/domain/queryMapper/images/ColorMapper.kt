package com.example.domain.queryMapper.images

import com.example.data.dto.ColorDetailsDto
import com.example.data.tables.ColorsTable
import org.ktorm.dsl.QueryRowSet

fun QueryRowSet.toColorDetailsMapper() = ColorDetailsDto(
    color_id = this[ColorsTable.id] ?: 0,
    color_name = this[ColorsTable.colorName] ?: "",
    color_hex = this[ColorsTable.colorHex] ?: "",
)