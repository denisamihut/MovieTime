package com.example.movieapp.ui.save_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSaveMoviesBinding
import com.example.movieapp.ui.tab_activity.ui.main.PlaceholderFragment
import com.example.movieapp.ui.tab_activity.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SaveMovieFragment : Fragment() {

    private val tabTitles = arrayOf(
        "Favorite",
        "Watched"
    )
    private var adapter: AdapterTabPager2? = null

    private var _binding: FragmentSaveMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val saveMovieViewModel =
            ViewModelProvider(this).get(SaveMovieViewModel::class.java)

        _binding = FragmentSaveMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.ft_view_pager)
        val tabs: TabLayout = view.findViewById(R.id.tabs)

        adapter = activity?.let {
            AdapterTabPager2(it)
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