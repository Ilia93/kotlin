package com.example.pocketbook.presentation

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class App {
    companion object {
        fun setLinearLayoutManager(
            context: Context?,
            orientation: Int
        ): LinearLayoutManager {
            val linearManager = LinearLayoutManager(context, orientation, false)
            return linearManager
        }
    }
}