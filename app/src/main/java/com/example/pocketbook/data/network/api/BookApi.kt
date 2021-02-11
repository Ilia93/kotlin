package com.example.pocketbook.data.network.api

import com.example.pocketbook.data.network.model.BookModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("/data/Book?pageSize=15")
    fun getBooks(): Call<List<BookModel>>
}
