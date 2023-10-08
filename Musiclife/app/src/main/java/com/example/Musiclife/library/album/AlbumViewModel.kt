package com.example.Musiclife.library.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Musiclife.entities.Album

class AlbumViewModel : ViewModel() {

    val albumLiveData = MutableLiveData<List<Album>>()

    init {
        albumLiveData.value = Album.getFakeData()
    }
}