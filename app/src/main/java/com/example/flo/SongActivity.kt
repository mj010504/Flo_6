package com.example.flo

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding
import com.google.gson.Gson
import java.util.Timer


class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding
    lateinit var song: Song
    lateinit var timer: Timer
    private var mediaPlayer: MediaPlayer? = null
    private var gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)

        binding.songBackIb.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("title", binding.songTitleTv.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.songPlayIv.setOnClickListener {
            setPlayerStatus(true)
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(false)
        }

        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            binding.songTitleTv.text = intent.getStringExtra("title")
            binding.songSingerTv.text = intent.getStringExtra("singer")
        }

    }

    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 60),
                intent.getBooleanExtra("isPlaying", false),
                intent.getStringExtra("music")!!
            )
        }
        startTimer()
    }

    private fun setPlayer(song: Song) {
        binding.songTitleTv.text = intent.getStringExtra("title")
        binding.songSingerTv.text = intent.getStringExtra("singer")
        binding.songStratTimeTv.text =
            String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text =
            String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressbarBackgroudView.progress = (song.second * 1000 / song.playTime)

        val music = resources.getIdentifier(song.music, "raw", this.packageName)
        mediaPlayer = MediaPlayer.create(this, music)

        setPlayerStatus(song.isPlaying)
    }


    private fun setPlayerStatus(isPlaying: Boolean) {

        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if (isPlaying) {
            binding.songPlayIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
            mediaPlayer?.start()
        } else {
            binding.songPlayIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }
    }

    private fun startTimer() {
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }
        inner class Timer(private val playTime: Int, var isPlaying: Boolean = true) : Thread() {
            private var second: Int = 0
            private var mills: Float = 0f

            override fun run() {
                super.run()
                try {
                    while (true) {
                        if (second >= playTime) {
                            break
                        }

                        if (isPlaying) {
                            sleep(50)
                            mills += 50

                            runOnUiThread {
                                binding.songProgressbarBackgroudView.progress =
                                    ((mills / playTime) * 100).toInt()
                            }

                            if (mills % 1000 == 0f) {
                                runOnUiThread {
                                    binding.songStratTimeTv.text =
                                        String.format("%02d:%02d", second / 60, second % 60)
                                }
                                second++
                            }
                        }

                    }
                } catch (e: InterruptedException) {

                }
            }
        }

        override fun onPause() {
            super.onPause()
            setPlayerStatus(false)
            song.second =
                ((binding.songProgressbarBackgroudView.progress * song.playTime) / 100) / 1000
            val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val songJson = gson.toJson(song)
            editor.putString("songData", songJson)
            editor.apply()
        }

        override fun onDestroy() {
            super.onDestroy()
            timer.interrupt()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }






