package com.example.Musiclife.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Musiclife.entities.Album
import com.example.Musiclife.entities.Artist
import com.example.Musiclife.entities.Music

class SearchViewModel : ViewModel() {

    val searchLiveData = MutableLiveData<List<Any>>()

    init {
        val dataList = mutableListOf<Any>()
        dataList.addAll(Music.getFakeData())
        dataList.addAll(Album.getFakeData())
        dataList.addAll(Artist.getFakeData())
        searchLiveData.value = dataList
    }
}