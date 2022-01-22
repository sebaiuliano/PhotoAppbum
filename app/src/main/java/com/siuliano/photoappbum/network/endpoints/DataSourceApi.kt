package com.siuliano.photoappbum.network.endpoints

import com.siuliano.photoappbum.models.Photo
import retrofit2.http.GET

interface DataSourceApi {
    @GET("/photos")
    suspend fun getPhotos() : List<Photo>
}