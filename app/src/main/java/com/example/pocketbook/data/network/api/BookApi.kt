package com.example.pocketbook.data.network.api

import com.example.pocketbook.data.network.model.book_model.BookModel
import com.example.pocketbook.data.network.model.book_model.BookRating
import retrofit2.Call
import retrofit2.http.*

interface BookApi {
    @GET("data/Book")
    fun getBooks(): Call<List<BookModel>>

    @GET("data/Book")
    fun getBookAuthor(@Query("bookAuthor") bookAuthor: String): Call<List<BookModel>>

    @GET("data/Book/count")
    fun getBooksCount(): Call<String>

    @PUT("data/Book/{objectId}")
    fun updateBookRating(@Path("objectId") objectId: String, @Body model: BookRating): Call<BookModel>
}
