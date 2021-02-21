package com.example.pocketbook.data.network.api

import com.example.pocketbook.data.network.model.BookModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("data/Book")
    fun getBooks(): Call<List<BookModel>>

    @GET("data/Book")
    fun getBookAuthor(@Query("bookAuthor") bookAuthor: String): Call<List<BookModel>>

    @GET("data/Book??property=Count(id)")
    fun getBooksCount(): Call<BookModel>

}
