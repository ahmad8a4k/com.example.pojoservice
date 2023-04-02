package com.example.data.tables

import com.example.data.entities.ImageNumDetails
import org.ktorm.schema.Table
import org.ktorm.schema.int

object ImageStaImageStatsDetails : Table<ImageNumDetails>("img_num_details") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val imageId = int("image_id").references(ImageDetailsTable) { it.imageId }
    val likesNum = int("likes_num").bindTo { it.likesNum }
    val shareNum = int("share_num").bindTo { it.sharNum }
    val downloadNum = int("download_num").bindTo { it.downloadNum }
    val watchNum = int("watch_num").bindTo { it.watchNum }
    val saveNum = int("save_num").bindTo { it.saveNum }
}