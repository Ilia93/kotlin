package com.example.pocketbook.presentation.screen.my_books.info.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.data.network.model.book_model.BookModel
import com.example.pocketbook.databinding.MyBooksBookInfoItemBinding
import com.example.pocketbook.presentation.screen.main.top.ItemListener

class MyBooksInfoHolder(
    itemView: View,
    private val listener: ItemListener<BookModel>,
    private val listOfItems: List<BookModel>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MyBooksBookInfoItemBinding.bind(itemView)

    fun bindItem() {
        binding.myBooksInfoItemImage.clipToOutline = true
        itemView.setOnClickListener {
            listener.itemClicked(listOfItems[adapterPosition])
        }
    }
}