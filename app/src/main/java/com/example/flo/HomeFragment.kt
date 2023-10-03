package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    var albumFragment = AlbumFragment()
    var bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.homePannelIUAlbumIv.setOnClickListener {
            bundle.putString("albumTitle", "아이유 정규 5집 LILAC")
            albumFragment.arguments = bundle

            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, albumFragment).commitAllowingStateLoss()

        }
        return binding.root
    }


}