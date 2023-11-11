package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemAlbumBinding
import com.example.flo.databinding.SavedSongBinding

class SavedSongRVAdapter(private val albumList: ArrayList<Album>): RecyclerView.Adapter<SavedSongRVAdapter.ViewHolder>(){

    interface MyItemClickListener{
        fun onRemoveItem(position: Int)
    }

    private lateinit var myItemClickLister: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        myItemClickLister = itemClickListener
    }

    fun addItem(album: Album) {
        albumList.add(album)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        albumList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SavedSongRVAdapter.ViewHolder {
        val binding: SavedSongBinding = SavedSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedSongRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position])

        holder.binding.menuBtnIv.setOnClickListener { myItemClickLister.onRemoveItem(position) }
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(val binding: SavedSongBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.titleTv.text = album.title
            binding.singerTv.text = album.singer
            binding.itemAlbumCoverIv.setImageResource(album.coverImg!!)
        }
    }


}