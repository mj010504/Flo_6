package com.example.flo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import layout.AlbumVpadapter


class AlbumFragment : Fragment() {
    lateinit var binding : FragmentAlbumBinding
    private val gson = Gson()
    private var information = arrayListOf("수록곡","상세정보","영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater,container, false)

        val albumJson = arguments?.getString("album")
        val album = gson.fromJson(albumJson, Album::class.java)
        setInit(album)

        val albumAdapter = AlbumVpadapter(this)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb,binding.albumContentVp){
            tab, position ->
            tab.text = information[position]
        }.attach()

//        val albumTitle = arguments?.getString("albumTitle")
//
//        if (!albumTitle.isNullOrEmpty()) {
//            Toast.makeText(activity, albumTitle, Toast.LENGTH_SHORT).show()
//        }

        binding.albumBeforeIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().
            replace(R.id.fragmentContainerView,HomeFragment()).commitAllowingStateLoss()
        }

        return binding.root
    }


    private fun setInit(album: Album) {
        binding.albumPictureIv.setImageResource(album.coverImg!!)
        binding.albumTitleTv.text = album.title.toString()
        binding.albumSingerTv.text = album.singer.toString()
    }

}