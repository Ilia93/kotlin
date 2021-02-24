package com.example.pocketbook.presentation.screen.main.top

import com.example.pocketbook.data.network.model.BookAuthorModel

interface ImageListener {
    fun imageClicked(model: BookAuthorModel)
}