package com.siuliano.photoappbum.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Album (
    val albumId: Int,
    val photos: List<Photo>
)