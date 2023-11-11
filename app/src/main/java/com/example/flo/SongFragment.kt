package com.example.flo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import com.example.flo.databinding.FragmentSongBinding


class SongFragment : Fragment() {

    lateinit var binding:FragmentSongBinding

    private var isImageChanged = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    binding = FragmentSongBinding.inflate(inflater,container,false)

        binding.albumToggleOffIv.setOnClickListener {
            if (isImageChanged) {
                binding.albumToggleOffIv.setImageResource(R.drawable.btn_toggle_off) // 이미지 변경
                isImageChanged = false
            } else {
                binding.albumToggleOffIv.setImageResource(R.drawable.btn_toggle_on) // 새로운 이미지로 변경
                isImageChanged = true
            }
        }

        return binding.root
    }

    // 이미지 클릭 이벤트 핸들러



}