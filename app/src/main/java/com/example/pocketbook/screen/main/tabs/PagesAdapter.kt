package com.example.pocketbook.screen.main.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagesAdapter(fragmentManager: FragmentManager?, context: Context?) :
    FragmentPagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> BookPageFragment.getInstance()
            1 -> AudioBookPageFragment.getInstance()
        }
        return BookPageFragment.getInstance()
    }
}