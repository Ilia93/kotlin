package com.example.pocketbook.screen.main.top

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.data.network.model.ImageCollectionModel
import com.example.pocketbook.databinding.MainTopCardViewItemBinding

class TopScreenHolder(
    itemView: View,
    private val itemClicked: ItemListener<ImageCollectionModel>,
    private val listOfItems: List<ImageCollectionModel>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MainTopCardViewItemBinding.bind(itemView)

    fun bindClickListener() {
        itemView.setOnClickListener {
            itemClicked.itemClicked(
                listOfItems[layoutPosition]
            )
        }
    }
}