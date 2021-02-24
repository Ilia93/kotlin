package com.example.pocketbook.presentation.screen.my_books.books_status

import android.graphics.drawable.Drawable

data class BooksStatusDataClass(
    val statusImage: Drawable?,
    val statusText: String,
    val booksStatusCount: Int
)
