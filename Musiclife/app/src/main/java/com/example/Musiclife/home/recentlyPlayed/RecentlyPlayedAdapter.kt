package com.example.Musiclife.home.recentlyPlayed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Musiclife.PlayerFragmentDirections
import com.example.Musiclife.entities.Music
import com.example.musiclife.databinding.ItemRecentlyPlayedBinding

class RecentlyPlayedAdapter : RecyclerView.Adapter<RecentlyPlayedAdapter.ViewHolder>() {

    var musics: List<Music> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecentlyPlayedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = musics.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recently = musics[position]
        holder.bind(recently)
    }

    inner class ViewHolder(private val binding: ItemRecentlyPlayedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(music: Music) {
            Glide.with(binding.root).load(music.imageUrl).into(binding.recentlyImage)
            binding.recentlyText.text = music.title
            binding.root.setOnClickListener { view ->
                val songTitle = music.title
                val artistName = music.artist
                val imageUrl = music.imageUrl
                val songUrl = music.trackUrl

                val action = PlayerFragmentDirections.actionGlobalPlayerFragment(
                    songTitle, artistName, songUrl, imageUrl
                )
                view.findNavController().navigate(action)
            }
        }
    }
}