package com.example.Musiclife.library

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.Musiclife.library.album.AlbumFragment
import com.example.Musiclife.library.artist.ArtistFragment
import com.example.Musiclife.library.song.SongFragment

class LibraryViewPagerAdapter(fm: FragmentManager, lf: Lifecycle) : FragmentStateAdapter(fm, lf) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) SongFragment() else if (position == 1) ArtistFragment() else AlbumFragment()
    }

}