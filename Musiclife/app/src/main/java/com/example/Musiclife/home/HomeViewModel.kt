package com.example.Musiclife.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Musiclife.entities.Album
import com.example.Musiclife.entities.Music

class HomeViewModel : ViewModel() {

    val goodAfternoonLiveData = MutableLiveData<List<Music>>()
    val recentlyPlayedLiveData = MutableLiveData<List<Music>>()
    val madeForYouLiveData = MutableLiveData<List<Album>>()

    init {
        goodAfternoonLiveData.value = Music.getFakeData().shuffled().take(6)
        recentlyPlayedLiveData.value = Music.getFakeData().shuffled()
        madeForYouLiveData.value = Album.getFakeData().shuffled()
    }
}