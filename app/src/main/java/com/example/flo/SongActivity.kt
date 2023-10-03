package com.example.flo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.songBackIb.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java).apply {
                putExtra("title", binding.songTitleTv.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.songPlayIv.setOnClickListener {
            binding.songPlayIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }

        binding.songPauseIv.setOnClickListener {
            binding.songPlayIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }

        if(intent.hasExtra("title") && intent.hasExtra("singer")) {
            binding.songTitleTv.text = intent.getStringExtra("title")
            binding.songSingerTv.text = intent.getStringExtra("singer")
        }

    }

}