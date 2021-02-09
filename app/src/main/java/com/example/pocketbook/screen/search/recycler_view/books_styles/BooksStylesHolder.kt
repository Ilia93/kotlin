package com.example.pocketbook.screen.search.recycler_view.books_styles

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.databinding.SearchScreenBookStylesItemBinding
import com.example.pocketbook.screen.main.top.ItemListener
import com.example.pocketbook.screen.search.recycler_view.books.BooksDataClass

class BooksStylesHolder(
    itemView: View,
    private val listOfItems: List<BooksDataClass>,
    private val itemListener: ItemListener<BooksDataClass>
) :
    RecyclerView.ViewHolder(itemView) {
    val binding = SearchScreenBookStylesItemBinding.bind(itemView)

    fun bindItem() {
        itemView.setOnClickListener(View.OnClickListener {
            itemListener.itemClicked(listOfItems[layoutPosition])
        })
    }
}