package com.siuliano.photoappbum.repositories

import com.siuliano.photoappbum.models.Album

interface DataSourceRepository {
    suspend fun getPhotos() : List<Album>
}