package com.example.pocketbook.presentation.screen.main.tabs.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.databinding.MainBooksTabItemCollectionTypeBinding
import com.example.pocketbook.presentation.screen.main.top.ItemListener

class TodayRecommendationHolder(
    itemView: View,
    private val itemListener: ItemListener<Any>,
    private val listOfBooks: List<Any>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MainBooksTabItemCollectionTypeBinding.bind(itemView)

    fun bindBooks() {
        itemView.setOnClickListener {
            itemListener.itemClicked(listOfBooks[adapterPosition])
        }
    }
}