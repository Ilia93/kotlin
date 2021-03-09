package com.example.pocketbook.presentation.screen.main.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.ImageCollectionFragmentBinding
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.BACKGROUND_IMAGE_URL
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.BUTTON_TEXT
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.HEADER_TEXT
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.IMAGE_ID
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.TAB_TEXT

class ImageCollectionFragment() : Fragment() {

    private lateinit var сollectionAdapter: ImageFragmentAdapter
    private lateinit var binding: ImageCollectionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ImageCollectionFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        сollectionAdapter = ImageFragmentAdapter(this)
        binding.viewPagerMain.adapter = сollectionAdapter
    }
}