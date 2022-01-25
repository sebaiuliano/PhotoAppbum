package com.siuliano.photoappbum.network.repositories

import com.siuliano.photoappbum.models.Album
import com.siuliano.photoappbum.network.endpoints.DataSourceApi
import com.siuliano.photoappbum.repositories.DataSourceRepository

class DataSourceRepositoryImpl(
    private val dataSourceApi : DataSourceApi
) : DataSourceRepository {
    override suspend fun getPhotos(): List<Album> {
        val response = dataSourceApi.getPhotos()
        val albumList : MutableList<Album> = ArrayList()
        if (response.isSuccessful) {
            response.body()?.let { photoList ->
                val groupedPhotos = photoList.groupBy { photo -> photo.albumId }
                for (key in groupedPhotos.keys) {
                    albumList.add(Album(key, groupedPhotos[key] ?: ArrayList()))
                }
            }
        }
        return albumList
    }
}