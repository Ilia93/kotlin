package com.example.pocketbook.screen.search.books_styles.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.screen.main.top.ItemListener

class BooksStylesAdapter(
    private val context: Context?,
    private val listOfItems: List<BooksStylesDataClass>,
    private val itemListener: ItemListener<BooksStylesDataClass>
) : RecyclerView.Adapter<BooksStylesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksStylesHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.search_screen_books_categories_item, parent, false)
        return BooksStylesHolder(view, itemListener, listOfItems)
    }

    override fun onBindViewHolder(holder: BooksStylesHolder, position: Int) {
        val model = listOfItems[position]
        holder.bindItem()
        holder.binding.searchScreenBooksCategoriesTag.text = model.categoryTag
        holder.binding.searchScreenBooksCategoriesCount.text = model.categoryCount
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
}