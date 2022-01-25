package com.siuliano.photoappbum.repositories

import androidx.lifecycle.MutableLiveData
import com.siuliano.photoappbum.models.Album

interface DataSourceRepository {
    suspend fun getPhotos() : List<Album>
}