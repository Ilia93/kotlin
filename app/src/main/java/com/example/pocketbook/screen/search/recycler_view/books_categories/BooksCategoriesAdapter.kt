package com.example.pocketbook.screen.search.recycler_view.books_categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.screen.main.top.ItemListener

class BooksCategoriesAdapter(
    private val context: Context,
    private val listOfItems: List<BooksCategoriesDataClass>,
   // private val itemListener: ItemListener<BooksCategoriesDataClass>

) : RecyclerView.Adapter<BooksCategoriesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksCategoriesHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.search_screen_book_categories_item, parent, false)
        return BooksCategoriesHolder(view, listOfItems)
    }

    override fun onBindViewHolder(holder: BooksCategoriesHolder, position: Int) {
        val model = listOfItems[position]
        //holder.bindItem()
        holder.binding.searchScreenBooksItemTag.text = model.booksTag
        holder.binding.searchScreenBooksCount.text = model.categoryCount.toString()
        holder.binding.searchScreenBooksItemImage.setImageDrawable(model.categoryImage)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
}