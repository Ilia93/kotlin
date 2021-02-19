package com.example.pocketbook.screen.main.top

import com.example.pocketbook.data.network.model.BookAuthorModel

interface ImageListener {
    fun imageClicked(model: BookAuthorModel)
}