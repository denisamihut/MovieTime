package com.denimihut.movieApp.my_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.denimihut.movieApp.R
import com.denimihut.movieApp.activity.SaveMovieViewModel
import com.denimihut.movieApp.adapter.AdapterTabPager
import com.denimihut.movieApp.databinding.FragmentMyMoviesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MyMoviesFragment : Fragment() {
    private val tabTitles = arrayOf(
        "Favorite",
        "Watched"
    )
    private var adapter: AdapterTabPager? = null

    private var _binding: FragmentMyMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[SaveMovieViewModel::class.java]
        _binding = FragmentMyMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.ft_view_pager)
        val tabs: TabLayout = view.findViewById(R.id.tabs)

        adapter = activity?.let {
            AdapterTabPager(it)
        }

        adapter?.addFragment(FavouriteListFragment(), tabTitles[0])
        adapter?.addFragment(WatchedListFragment(), tabTitles[1])

        viewPager.adapter = adapter

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = adapter?.getTitle(position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}