package com.example.pocketbook.data.network.api

import com.example.pocketbook.data.network.model.BookAuthorModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAuthorApi {
    @GET("data/BookAuthor")
    fun getBookAuthor(): Call<List<BookAuthorModel>>
}