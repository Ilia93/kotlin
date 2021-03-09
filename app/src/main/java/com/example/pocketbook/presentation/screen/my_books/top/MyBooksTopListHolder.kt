package com.example.pocketbook.presentation.screen.my_books.top

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.data.network.model.book_model.BookModel
import com.example.pocketbook.databinding.MyBooksTopRecyclerViewItemBinding
import com.example.pocketbook.presentation.screen.main.top.ItemListener

class MyBooksTopListHolder(
    itemView: View,
    private val listener: ItemListener<BookModel>,
    private val listOfItems: List<BookModel>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MyBooksTopRecyclerViewItemBinding.bind(itemView)

    fun bindItemListener() {
        itemView.setOnClickListener {
            listener.itemClicked(listOfItems[layoutPosition])
        }
    }
}