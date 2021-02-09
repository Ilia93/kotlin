package com.example.pocketbook.screen.search.books_styles.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.databinding.SearchScreenBooksCategoriesItemBinding
import com.example.pocketbook.screen.main.top.ItemListener

class BooksStylesHolder(
    itemView: View,
    private val itemListener: ItemListener<BooksStylesDataClass>,
    private val listOfItems: List<BooksStylesDataClass>
) : RecyclerView.ViewHolder(itemView) {
    val binding: SearchScreenBooksCategoriesItemBinding =
        SearchScreenBooksCategoriesItemBinding.bind(itemView)

    fun bindItem() {
        itemView.setOnClickListener(View.OnClickListener {
            itemListener.itemClicked(listOfItems[adapterPosition])
        })
    }
}