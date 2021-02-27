package com.example.pocketbook.presentation.screen.main.tabs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.presentation.screen.main.tabs.holders.ButtonsHolder
import com.example.pocketbook.presentation.screen.main.tabs.holders.ImageHolder
import com.example.pocketbook.presentation.screen.main.tabs.holders.TodayRecommendationHolder
import com.example.pocketbook.presentation.screen.main.top.ItemListener
/*
class ComplexRecyclerViewAdapter(
    private val context: Context,
    private val listOfObjects: MutableList<Any>,
    private val itemListener: ItemListener<Any>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val COLLECTION = 0
    private val BUTTONS_TYPE = 1
    private val IMAGE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(context)
        val view: View
        when (viewType) {
            COLLECTION ->
                view = inflater.inflate(R.layout.main_books_tab_item_collection_type, parent, false)
            holder = TodayRecommendationHolder(view, itemListener, listOfObjects),
            BUTTONS_TYPE ->
                view = inflater.inflate(R.layout.main_books_tab_item_collection_type, parent, false)
            holder = ButtonsHolder(view, itemListener, listOfObjects),
            IMAGE ->
                view = inflater.inflate(R.layout.main_books_tab_item_collection_type, parent, false)
            holder = ImageHolder(view, itemListener, listOfObjects),

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listOfObjects.size
    }
}*/