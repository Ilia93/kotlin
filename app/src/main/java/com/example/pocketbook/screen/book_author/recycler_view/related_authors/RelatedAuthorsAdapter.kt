package com.example.pocketbook.screen.book_author.recycler_view.related_authors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.pocketbook.R
import com.example.pocketbook.data.network.model.BookAuthorModel
import com.example.pocketbook.screen.main.top.ImageListener
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class RelatedAuthorsAdapter(
    private val context: Context?,
    private val listOfAuthors: List<BookAuthorModel>,
    private val itemListener: ImageListener
) : RecyclerView.Adapter<RelatedAuthorsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedAuthorsHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.book_author_related_authors_item, parent, false)
        return RelatedAuthorsHolder(view, itemListener, listOfAuthors)
    }

    override fun onBindViewHolder(holder: RelatedAuthorsHolder, position: Int) {
        val model:BookAuthorModel = listOfAuthors[position]
        holder.bindImage()
        holder.binding.bookAuthorRelatedAuthorName.text = model.bookAuthor
        holder.binding.bookAuthorRelatedAuthorBooksCount.text = model.bookAuthorStyle
        if (context != null) {
            Glide.with(context).load(model.bookAuthorImageUrl)
                .into(holder.binding.bookAuthorRelatedBookImage)
        }
    }

    override fun getItemCount(): Int {
        return listOfAuthors.size
    }
}