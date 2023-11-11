package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.ActivityMainBinding
import com.example.flo.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import layout.HomePannelVPadapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var mainActivityBinding: ActivityMainBinding
    private var albumDatas = ArrayList<Album>()

    var albumFragment = AlbumFragment()
    var bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        mainActivityBinding = (activity as? MainActivity)!!.binding
//        binding.homePannelIUAlbumIv.setOnClickListener {
//            bundle.putString("albumTitle", "아이유 정규 5집 LILAC")
//            albumFragment.arguments = bundle
//
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainerView, albumFragment).commitAllowingStateLoss()
//
//        }

        // 데이터 리스트 생성 더미 데이터
        albumDatas.apply {
            add(Album("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(Album("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "방탄소년단 (BTS)", R.drawable.img_album_exp4))
            add(Album("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp5))
            add(Album("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp6))
        }

        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        binding.homeTodayAlbumRv.adapter = albumRVAdapter
        binding.homeTodayAlbumRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        albumRVAdapter.setMyItemClickListener(object: AlbumRVAdapter.MyItemClickListener{
            override fun onItemCLick(album: Album) {
                changerAlbumFragment(album)
            }

            override fun onPlayBtnClick(album: Album) {
                mainActivityBinding.mainPlayerTitleTv.text = album.title.toString()
                mainActivityBinding.mainPlayerSingerTv.text = album.singer.toString()
            }
        })

        val bannerAdapter = BannerVPadapter(this)
        bannerAdapter.addFragment(bannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(bannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.bannerVp.adapter = bannerAdapter
        binding.bannerVp.orientation =  ViewPager2.ORIENTATION_HORIZONTAL

        val homePannelAdapter = HomePannelVPadapter(this)
        homePannelAdapter.addFragment(HomePannelFragment(R.drawable.img_first_album_default))
        homePannelAdapter.addFragment(HomePannelFragment(R.drawable.img_home_viewpager_exp2))
        homePannelAdapter.addFragment(HomePannelFragment(R.drawable.img_home_viewpager_exp))
        homePannelAdapter.addFragment(HomePannelFragment(R.drawable.iu_album))
        binding.homePanelVp.adapter = homePannelAdapter
        binding.homePanelVp.orientation =  ViewPager2.ORIENTATION_HORIZONTAL


        TabLayoutMediator(binding.homePannelTb, binding.homePanelVp) { tab, position ->
            //Some implementation
        }.attach()


        return binding.root
    }

    private fun changerAlbumFragment(album: Album) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, albumFragment.apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(album)
                    putString("album", albumJson)
                }
            })
            .commitAllowingStateLoss()
    }


}