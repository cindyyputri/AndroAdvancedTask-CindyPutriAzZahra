package com.example.taskadvanced.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.taskadvanced.R
import com.example.taskadvanced.adapters.TLAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        val tlAdapter = TLAdapter(requireActivity())
        tlAdapter.addFragment(NowPlayingFragment(), "Now Playing")
        tlAdapter.addFragment(UpComingFragment(), "Upcoming")
        viewPager.adapter = tlAdapter

        // Menambahkan mediator untuk mengaitkan TabLayout dengan ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tlAdapter.getPageTitle(position)
        }.attach()

        return view
    }

}