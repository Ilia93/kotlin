package com.example.pocketbook.presentation.screen.main.tabs.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.databinding.MainBooksTabItemImageTypeBinding
import com.example.pocketbook.presentation.screen.main.tabs.data_classes.ImageHolderDataClass
import com.example.pocketbook.presentation.screen.main.top.ItemListener

class ImageHolder(
    itemView: View,
    private val itemListener: ItemListener<Any>,
    private val listOfImages: List<Any>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MainBooksTabItemImageTypeBinding.bind(itemView)

    fun bindClickedImage() {
        itemView.setOnClickListener {
            itemListener.itemClicked(listOfImages[adapterPosition])
        }
    }
}