package com.example.pocketbook.screen.book_author

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.RelatedBooksItemBinding
import com.example.pocketbook.screen.main.top.ItemListener

class RelatedBooksHolder(
    itemView: View,
    private val list: List<BookModel>,
    private val itemListener: ItemListener<BookModel>
) : RecyclerView.ViewHolder(itemView) {
    val binding = RelatedBooksItemBinding.bind(itemView)

    fun bindItemListener() {
        itemView.setOnClickListener(View.OnClickListener {
            itemListener.itemClicked(list[layoutPosition])
        })
    }
}