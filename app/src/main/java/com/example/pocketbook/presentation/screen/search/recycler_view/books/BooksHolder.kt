package com.example.pocketbook.presentation.screen.search.recycler_view.books

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.databinding.SearchScreenBooksItemBinding
import com.example.pocketbook.presentation.screen.main.top.ItemListener

class BooksHolder(
    itemView: View,
    private val itemListener: ItemListener<BooksDataClass>,
    private val listOfItems: List<BooksDataClass>
) : RecyclerView.ViewHolder(itemView) {
    val binding = SearchScreenBooksItemBinding.bind(itemView)

    fun bindItem() {
        itemView.setOnClickListener(View.OnClickListener {
            itemListener.itemClicked(listOfItems[layoutPosition])
        })
    }
}