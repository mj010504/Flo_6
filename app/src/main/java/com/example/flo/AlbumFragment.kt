package com.example.flo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding


class AlbumFragment : Fragment() {
    lateinit var binding : FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater,container, false)

        val albumTitle = arguments?.getString("albumTitle")

        if (!albumTitle.isNullOrEmpty()) {
            Toast.makeText(activity, albumTitle, Toast.LENGTH_SHORT).show()
        }

        binding.albumBeforeIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().
            replace(R.id.fragmentContainerView,HomeFragment()).commitAllowingStateLoss()
        }



        return binding.root
    }
}