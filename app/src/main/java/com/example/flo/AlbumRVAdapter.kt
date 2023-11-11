package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemAlbumBinding

class AlbumRVAdapter(private val albumList: ArrayList<Album>) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemCLick(album: Album)
        fun onPlayBtnClick(album: Album)
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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumRVAdapter.ViewHolder {
        val binding: ItemAlbumBinding = ItemAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumRVAdapter.ViewHolder, position: Int) {
       holder.bind(albumList[position])

        holder.itemView.setOnClickListener{myItemClickLister.onItemCLick(albumList[position]) }
        holder.binding.playBtnIv.setOnClickListener { myItemClickLister.onPlayBtnClick(albumList[position]) }
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.titleTv.text = album.title
            binding.singerTv.text = album.singer
            binding.itemAlbumCoverIv.setImageResource(album.coverImg!!)
        }
    }
}