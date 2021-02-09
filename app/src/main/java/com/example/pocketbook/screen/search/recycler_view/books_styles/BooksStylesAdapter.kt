package com.example.pocketbook.screen.search.recycler_view.books_styles

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.screen.main.top.ItemListener
import com.example.pocketbook.screen.search.recycler_view.books.BooksDataClass

class BooksStylesAdapter(
    private val context: Context,
    private val listOfItems: List<BooksDataClass>,
    private val itemListener: ItemListener<BooksDataClass>

) : RecyclerView.Adapter<BooksStylesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksStylesHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.search_screen_book_styles_item, parent, false)
        return BooksStylesHolder(view, listOfItems, itemListener)
    }

    override fun onBindViewHolder(holder: BooksStylesHolder, position: Int) {
        val model = listOfItems[position]
        holder.bindItem()
        holder.binding.searchScreenBooksItemTag.text = model.category
        holder.binding.searchScreenBooksCount.text = model.booksCount.toString()
        holder.binding.searchScreenBooksItemImage.setImageDrawable(model.booksImage)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
}