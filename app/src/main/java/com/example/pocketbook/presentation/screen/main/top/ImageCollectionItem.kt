package com.example.pocketbook.presentation.screen.main.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pocketbook.databinding.ImageCollectionItemBinding

class ImageCollectionItem : Fragment() {

    companion object {
        const val BACKGROUND_IMAGE_URL = "backGroundUrl"
        const val BUTTON_TEXT = "buttonText"
        const val HEADER_TEXT = "headerText"
        const val IMAGE_ID = "imgId"
        const val TAB_TEXT = "tabText"

        fun getInstance(
            imageId: String?,
            tabText: String?,
            headerText: String?,
            buttonText: String?,
            backGroundImageUrl: String?
        ): ImageCollectionItem {
            val fragment = ImageCollectionItem()
            fragment.arguments?.apply {
                putString(IMAGE_ID, imageId)
                putString(TAB_TEXT, tabText)
                putString(HEADER_TEXT, headerText)
                putString(BUTTON_TEXT, buttonText)
                putString(BACKGROUND_IMAGE_URL, backGroundImageUrl)
            }
            return fragment
        }
    }

    private lateinit var binding: ImageCollectionItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ImageCollectionItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            Glide.with(it).load(arguments?.getString(BACKGROUND_IMAGE_URL))
                .into(binding.imageActivityImg)
        }
        binding.imageActivityTextTag.text = arguments?.getString(HEADER_TEXT)
        binding.imageActivityText.text = arguments?.getString(TAB_TEXT)
        binding.imageScreenActionButton.text = arguments?.getString(BUTTON_TEXT)
    }
}