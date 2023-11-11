package layout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flo.DetailFragment
import com.example.flo.SongFragment
import com.example.flo.VideoFragment

class AlbumVpadapter(frament: Fragment) : FragmentStateAdapter(frament) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongFragment()
            1 -> DetailFragment()
            else -> VideoFragment()
        }
    }


}