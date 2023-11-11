package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentHomeBinding
import com.example.flo.databinding.FragmentSavedSongBinding
import com.example.flo.databinding.SavedSongBinding


class SavedSongFragment : Fragment() {

    lateinit var binding : FragmentSavedSongBinding
    private var albumDatas = ArrayList<Album>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSavedSongBinding.inflate(inflater,container,false)
        // 데이터 리스트 생성 더미 데이터
        albumDatas.apply {
            add(Album("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(Album("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "방탄소년단 (BTS)", R.drawable.img_album_exp4))
            add(Album("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp5))
            add(Album("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp6))
        }

        val savedSongRVAdapter = SavedSongRVAdapter(albumDatas)
        binding.homeSavedSongRv.adapter = savedSongRVAdapter
        binding.homeSavedSongRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        savedSongRVAdapter.setMyItemClickListener(object: SavedSongRVAdapter.MyItemClickListener{
            override fun onRemoveItem(position: Int) {
                savedSongRVAdapter.removeItem(position)
            }

        })

        return binding.root
    }

}