package com.example.movieapp.ui.save_movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterTabPager2(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val nFragmentList: MutableList<Fragment> = mutableListOf()
    private val nFragmentTitleList: MutableList<String> = mutableListOf()

    override fun getItemCount(): Int = nFragmentList.size

    override fun createFragment(position: Int): Fragment = nFragmentList[position]

    fun addFragment(fragment: Fragment, title: String) {
        nFragmentList.add(fragment)
        nFragmentTitleList.add(title)
    }

    fun getTitle(position: Int) = nFragmentTitleList[position]
}
