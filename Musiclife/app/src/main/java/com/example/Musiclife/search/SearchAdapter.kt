package com.example.Musiclife.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Musiclife.PlayerFragmentDirections
import com.example.Musiclife.entities.Album
import com.example.Musiclife.entities.Artist
import com.example.Musiclife.entities.Music
import com.example.musiclife.databinding.ItemAlbumSearchBinding
import com.example.musiclife.databinding.ItemArtistSearchBinding
import com.example.musiclife.databinding.ItemMusicSearchBinding
import java.util.Locale

class SearchAdapter(private val listener: OnFilteredListListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var dataList: List<Any> = emptyList()
    var filteredList = dataList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val musicBinding =
            ItemMusicSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val albumBinding =
            ItemAlbumSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val artistBinding =
            ItemArtistSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (viewType) {
            VIEW_TYPE_MUSIC -> {
                MusicViewHolder(musicBinding)
            }

            VIEW_TYPE_ALBUM -> {
                AlbumViewHolder(albumBinding)
            }

            VIEW_TYPE_ARTIST -> {
                ArtistViewHolder(artistBinding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    class MusicViewHolder(private val binding: ItemMusicSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(music: Music) {
            Glide.with(binding.root).load(music.imageUrl).into(binding.musicImage)
            binding.musicName.text = music.title
            binding.artistName.text = buildString {
                append("Song · ")
                append(music.artist)
            }
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

    class AlbumViewHolder(private val binding: ItemAlbumSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            Glide.with(binding.root).load(album.imageSrc).into(binding.albumImage)
            binding.albumName.text = album.name
            binding.artistName.text = buildString {
                append("Album · ")
                append(album.artist)
            }
        }
    }

    class ArtistViewHolder(private val binding: ItemArtistSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(artist: Artist) {
            Glide.with(binding.root).load(artist.imageSrc).into(binding.artistImage)
            binding.artistName.text = buildString {
                append("Artist · ")
                append(artist.name)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = filteredList[position]
        when (holder.itemViewType) {
            VIEW_TYPE_MUSIC -> {
                val musicHolder = holder as MusicViewHolder
                val musicData = item as Music
                musicHolder.bind(musicData)
            }

            VIEW_TYPE_ALBUM -> {
                val albumHolder = holder as AlbumViewHolder
                val albumData = item as Album
                albumHolder.bind(albumData)
            }

            VIEW_TYPE_ARTIST -> {
                val artistHolder = holder as ArtistViewHolder
                val artistData = item as Artist
                artistHolder.bind(artistData)
            }
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (filteredList[position]) {
            is Music -> VIEW_TYPE_MUSIC
            is Album -> VIEW_TYPE_ALBUM
            is Artist -> VIEW_TYPE_ARTIST
            else -> throw IllegalArgumentException("Invalid data type")
        }
    }

    companion object {
        private const val VIEW_TYPE_MUSIC = 0
        private const val VIEW_TYPE_ALBUM = 1
        private const val VIEW_TYPE_ARTIST = 2
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase(Locale.getDefault())
                val filteredData: List<Any>
                if (query.isEmpty()) {
                    filteredData = emptyList()
                } else {
                    filteredData = dataList.filter {
                        when (it) {
                            is Music -> {
                                it.title.plus(it.artist).lowercase(Locale.getDefault())
                                    .contains(query)
                            }

                            is Album -> {
                                it.name.plus(it.artist).lowercase(Locale.getDefault())
                                    .contains(query)
                            }

                            is Artist -> {
                                it.name.lowercase(Locale.getDefault()).contains(query)
                            }

                            else -> {
                                throw IllegalArgumentException("Invalid data type")
                            }
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredData
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Any>
                notifyDataSetChanged()

                listener.onFilteredList(filteredList)
            }
        }
    }

    interface OnFilteredListListener {
        fun onFilteredList(filteredData: List<Any>)
    }
}
