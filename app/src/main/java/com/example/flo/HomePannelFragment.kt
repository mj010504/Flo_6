package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentBannerBinding
import com.example.flo.databinding.FragmentHomePannelBinding

class HomePannelFragment(val imgRes: Int) : Fragment() {

    lateinit var binding: FragmentHomePannelBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePannelBinding.inflate(inflater,container,false)

        binding.homePannelBackgroundIv.setImageResource(imgRes)
        return binding.root
    }


}