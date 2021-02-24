package com.example.pocketbook.presentation.screen.my_books.books_status

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.presentation.screen.my_books.BookMenuListener

class BooksStatusAdapter(
    private val context: Context?,
    private val menuListener: BookMenuListener<BooksStatusDataClass>,
    private val listOfMenu: MutableList<BooksStatusDataClass>
) : RecyclerView.Adapter<BooksStatusHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksStatusHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.my_books_status_recycler_view_item, parent, false)
        return BooksStatusHolder(view, menuListener, listOfMenu)
    }

    override fun onBindViewHolder(holder: BooksStatusHolder, position: Int) {
        val model = listOfMenu[position]
        holder.bindItem()
        holder.binding.myBooksWantReadCount.text = model.booksStatusCount.toString()
        holder.binding.myBooksWantToRead.text = model.statusText
        holder.binding.myBooksWantToRead.setCompoundDrawablesWithIntrinsicBounds(
            model.statusImage,
            null,
            null,
            null
        )
    }

    override fun getItemCount(): Int {
        return listOfMenu.size
    }
}