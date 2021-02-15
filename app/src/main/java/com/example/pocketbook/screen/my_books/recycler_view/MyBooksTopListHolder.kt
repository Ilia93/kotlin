package com.example.pocketbook.screen.my_books.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.MyBooksTopRecyclerViewItemBinding
import com.example.pocketbook.screen.main.top.ItemListener

class MyBooksTopListHolder(
    itemView: View,
    private val listener: ItemListener<BookModel>,
    private val listOfItems: List<BookModel>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MyBooksTopRecyclerViewItemBinding.bind(itemView)

    fun bindItemListener() {
        itemView.setOnClickListener(View.OnClickListener {
            listener.itemClicked(listOfItems[layoutPosition])
        })
    }
}