package com.example.flo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
     lateinit var binding: ActivityMainBinding
    private var song: Song = Song()
    private var gson: Gson = Gson()

    private val getResultText = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result -> if(result.resultCode == Activity.RESULT_OK) {
        var resultString = result.data?.getStringExtra("title")
        Toast.makeText(this,resultString,Toast.LENGTH_SHORT).show()
    }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FLo)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.mainPlayer.setOnClickListener {
            //startActivity(Intent(this, SongActivity::class.java))
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title",song.title)
            intent.putExtra("singer",song.singer)
            intent.putExtra("second",song.second)
            intent.putExtra("playTime",song.playTime)
            intent.putExtra("isPlaying",song.isPlaying)
            intent.putExtra("music", song.music)
            getResultText.launch(intent)
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        //네비게이션 컨트롤러
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        bottomNav.setupWithNavController(navController)
    }

    private fun setMinPlayer(song: Song) {
        binding.mainPlayerTitleTv.text = song.title
        binding.mainPlayerSingerTv.text = song.singer
        binding.mainSeekBarSb.progress = (song.second * 100000)/song.playTime
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val songJson = sharedPreferences.getString("songData", null)

        song = if(songJson == null) {
            Song("라일락","아이유(IU)", 0, 204, false, "music_lilac")
        }
        else {
            gson.fromJson(songJson, Song::class.java)
        }
        setMinPlayer(song)
    }

}