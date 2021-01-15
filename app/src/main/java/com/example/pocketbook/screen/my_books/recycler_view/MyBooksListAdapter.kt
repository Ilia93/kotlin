package com.example.pocketbook.screen.my_books.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pocketbook.R
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.screen.main.top.ItemListener
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MyBooksListAdapter(
    private val context: Context?,
    private val listener: ItemListener<BookModel>,
    private val listOfItems: List<BookModel>
) : RecyclerView.Adapter<MyBooksListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBooksListHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.my_books_top_recycler_view_item, parent, false)
        return MyBooksListHolder(view, listener, listOfItems)
    }

    override fun onBindViewHolder(holder: MyBooksListHolder, position: Int) {
        val model = listOfItems[position]
        holder.bindItemListener()
        if (context != null) {
            Glide.with(context)
                .load(model.imageUrl)
                .transform(RoundedCornersTransformation(10, 10))
                .into(holder.binding.myBooksTopRecyclerViewImage)
        }
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
}