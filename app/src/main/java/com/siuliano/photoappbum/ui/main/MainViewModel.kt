package com.siuliano.photoappbum.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siuliano.photoappbum.models.Album
import com.siuliano.photoappbum.models.Photo
import com.siuliano.photoappbum.repositories.DataSourceRepository
import kotlinx.coroutines.*

class MainViewModel(
    private val dataSourceRepository: DataSourceRepository
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _photosMutableHandler : MutableLiveData<List<Album>> = MutableLiveData()
    val photosMutableHandler : LiveData<List<Album>>
        get() = _photosMutableHandler

    var selectedAlbum : Album? = null
    var selectedPhoto : Photo? = null

    fun getPhotos() {
        uiScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataSourceRepository.getPhotos()
            }
            _photosMutableHandler.postValue(response)
        }
    }

}