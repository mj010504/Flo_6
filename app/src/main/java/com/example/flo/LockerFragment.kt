package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentAlbumBinding
import com.example.flo.databinding.FragmentLockerBinding
import com.google.android.material.tabs.TabLayoutMediator
import layout.AlbumVpadapter


class LockerFragment : Fragment() {

    lateinit var binding: FragmentLockerBinding

    private var information = arrayListOf("저장한 곡","음악파일")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater,container, false)

        val lockerAdapter = lockervpAdapter(this)
        binding.lockerVp.adapter = lockerAdapter
        TabLayoutMediator(binding.lockerTb,binding.lockerVp){
                tab, position ->
                tab.text = information[position]
        }.attach()

        return binding.root
    }

}