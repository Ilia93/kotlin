package com.example.pocketbook.screen.my_books.info.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.screen.main.top.ItemListener

class MyBooksInfoAdapter(
    val context: Context,
    val listener: ItemListener<BookModel>,
    val listOfItems: List<BookModel>
) : RecyclerView.Adapter<MyBooksInfoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBooksInfoHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.my_books_book_info_item, parent, false)
        return MyBooksInfoHolder(view, listener, listOfItems)
    }

    override fun onBindViewHolder(holder: MyBooksInfoHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
}