package com.example.pocketbook.presentation.screen.main.top

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.BACKGROUND_IMAGE_URL
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.BUTTON_TEXT
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.HEADER_TEXT
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.IMAGE_ID
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.TAB_TEXT

class ImageFragmentAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        val fragment = ImageCollectionItem()
        fragment.arguments?.apply {
            putString(IMAGE_ID, fragment.arguments?.getString(IMAGE_ID))
            putString(TAB_TEXT, fragment.arguments?.getString(TAB_TEXT))
            putString(HEADER_TEXT, fragment.arguments?.getString(HEADER_TEXT))
            putString(BUTTON_TEXT, fragment.arguments?.getString(BUTTON_TEXT))
            putString(BACKGROUND_IMAGE_URL, fragment.arguments?.getString(BACKGROUND_IMAGE_URL))
        }
        return fragment
    }
}