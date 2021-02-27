package com.example.pocketbook.presentation.screen.main.tabs.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.databinding.MainBooksTabItemButtonsTypeBinding
import com.example.pocketbook.presentation.screen.main.tabs.data_classes.ButtonsHolderDataClass
import com.example.pocketbook.presentation.screen.main.top.ItemListener

class ButtonsHolder(
    itemView: View,
    private val itemListener: ItemListener<Any>,
    private val listOfButtons: List<Any>
) : RecyclerView.ViewHolder(itemView) {
    val binding = MainBooksTabItemButtonsTypeBinding.bind(itemView)

    fun bindClickedButton() {
        itemView.setOnClickListener {
            itemListener.itemClicked(listOfButtons[adapterPosition])
        }
    }
}