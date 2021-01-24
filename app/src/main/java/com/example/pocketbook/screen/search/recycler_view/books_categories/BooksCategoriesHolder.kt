package com.example.pocketbook.screen.search.recycler_view.books_categories

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.databinding.SearchScreenBookCategoriesItemBinding
import com.example.pocketbook.screen.main.top.ItemListener

class BooksCategoriesHolder(
    itemView: View,
    private val listOfItems: List<BooksCategoriesDataClass>,
    //private val itemListener: ItemListener<BooksCategoriesDataClass>
) :
    RecyclerView.ViewHolder(itemView) {
    val binding = SearchScreenBookCategoriesItemBinding.bind(itemView)

    /*fun bindItem() {
        itemView.setOnClickListener(View.OnClickListener {
            itemListener.itemClicked(listOfItems[layoutPosition])
        })
    }*/
}