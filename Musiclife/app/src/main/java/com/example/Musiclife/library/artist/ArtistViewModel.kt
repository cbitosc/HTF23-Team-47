package com.example.Musiclife.library.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Musiclife.entities.Artist

class ArtistViewModel : ViewModel() {

    val artistLiveData = MutableLiveData<List<Artist>>()

    init {
        artistLiveData.value = Artist.getFakeData()
    }
}