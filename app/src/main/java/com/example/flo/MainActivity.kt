package com.example.flo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var binding: ActivityMainBinding

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val song = Song(binding.mainPlayerTitleTv.text.toString(), binding.mainPlayerSingerTv.text.toString())


        binding.mainPlayer.setOnClickListener {
            //startActivity(Intent(this, SongActivity::class.java))
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title",song.title)
            intent.putExtra("singer",song.singer)
            getResultText.launch(intent)
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        //네비게이션 컨트롤러
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        //앱바 설정객체
        appBarConfiguration =  AppBarConfiguration(setOf(R.id.homeFragment,R.id.lookingFragment,R.id.searchingFragment,R.id.lockerFragment))

        bottomNav.setupWithNavController(navController)

    }
}