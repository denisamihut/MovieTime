package com.denimihut.movieApp.ui.save_movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterTabPager(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val movieFragmentList: MutableList<Fragment> = mutableListOf()
    private val movieFragmentTitleList: MutableList<String> = mutableListOf()

    override fun getItemCount(): Int = movieFragmentList.size
    override fun createFragment(position: Int): Fragment = movieFragmentList[position]

    fun addFragment(fragment: Fragment, title: String) {
        movieFragmentList.add(fragment)
        movieFragmentTitleList.add(title)
    }

    fun getTitle(position: Int) = movieFragmentTitleList[position]
}
