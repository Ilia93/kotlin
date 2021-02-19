package com.example.pocketbook.screen.book_author.recycler_view.related_authors

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.data.network.model.BookAuthorModel
import com.example.pocketbook.databinding.BookAuthorRelatedAuthorsItemBinding
import com.example.pocketbook.screen.main.top.ImageListener

class RelatedAuthorsHolder(
    itemView: View,
    private val itemListener: ImageListener,
    private val listOfAuthors: List<BookAuthorModel>
) : RecyclerView.ViewHolder(itemView) {
    val binding = BookAuthorRelatedAuthorsItemBinding.bind(itemView)

    fun bindImage() {
        itemView.setOnClickListener(View.OnClickListener {
            itemListener.imageClicked(listOfAuthors[adapterPosition])
        })
    }
}