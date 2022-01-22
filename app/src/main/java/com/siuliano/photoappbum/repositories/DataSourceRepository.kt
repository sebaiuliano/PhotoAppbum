package com.siuliano.photoappbum.repositories

import com.siuliano.photoappbum.models.Photo

interface DataSourceRepository {
    suspend fun getPhotos() : List<Photo>
}