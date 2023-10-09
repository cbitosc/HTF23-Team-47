package com.example.MusicSenso

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.MusicSenso.databinding.RecyclerItemBinding

class MusicListAdapter(val songList: ArrayList<AudioModel>,val context: Context):RecyclerView.Adapter<MusicListAdapter.ItemViewHolder>() {

    private var binding: RecyclerItemBinding? = null

    inner class ItemViewHolder(itemBinding: RecyclerItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val songData: AudioModel = songList[position]
        val iconImageView = binding?.iconView


        holder.itemView.apply {
            binding?.musicTitleText?.text = songData.title
        }

        //to enable the playing of music when we click the song
        holder.itemView.setOnClickListener { it
            MyMediaPalyer.getInstance()?.reset()
            MyMediaPalyer.currentIndex = position

            val intent:Intent = Intent(context,MusicPlayerActivity::class.java)
            intent.putExtra("LIST",songList)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        //to highlight the music being played on the recycler list
        if(MyMediaPalyer.currentIndex == position){
            holder.itemView.apply {
                binding?.musicTitleText?.setTextColor(Color.parseColor("#F3DF26"))
            }
        }
        else{
            holder.itemView.apply {
                binding?.musicTitleText?.setTextColor(Color.parseColor("#1B9CA1"))
            }
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }


    //if there are large values in recycler view use these 2 functions to optimize
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}