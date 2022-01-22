package com.siuliano.photoappbum.network.repositories

import com.siuliano.photoappbum.models.Photo
import com.siuliano.photoappbum.network.endpoints.DataSourceApi
import com.siuliano.photoappbum.repositories.DataSourceRepository

class DataSourceRepositoryImpl(
    private val dataSourceApi : DataSourceApi
) : DataSourceRepository {
    override suspend fun getPhotos(): List<Photo> {
        return dataSourceApi.getPhotos()
    }
}