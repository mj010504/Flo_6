package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class lockervpAdapter(frament: Fragment) : FragmentStateAdapter(frament) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SavedSongFragment()
            else -> MusicFileFragment()
        }
    }


}