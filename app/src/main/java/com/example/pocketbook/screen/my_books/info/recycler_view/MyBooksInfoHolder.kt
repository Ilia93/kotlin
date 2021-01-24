package com.example.pocketbook.screen.my_books.info.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.MyBooksBooksInfoFragmentBinding
import com.example.pocketbook.screen.main.top.ItemListener

class MyBooksInfoHolder(
    itemView: View,
    private val listener: ItemListener<BookModel>,
    private val listOfItems: List<BookModel>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MyBooksBooksInfoFragmentBinding.bind(itemView)

    private fun bindItemListener() {
        itemView.setOnClickListener(View.OnClickListener {
            listener.itemClicked(listOfItems[adapterPosition])
        })
    }
}