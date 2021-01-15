package com.example.pocketbook.data.network.api

import com.example.pocketbook.data.network.model.BookModel
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {
    @GET("data/book")
    fun getBooks(): Call<List<BookModel>>
}
