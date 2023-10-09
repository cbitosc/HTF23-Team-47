package com.example.MusicSenso

import android.media.MediaPlayer

object MyMediaPalyer {

    // index property
    var currentIndex = -1

    var instance: MediaPlayer? = null
    // instance property
    @JvmName("getInstance1")
    fun getInstance(): MediaPlayer? {
        if (instance == null) {
            //then we create a new instance
            instance = MediaPlayer()
        }
        return instance
    }
}