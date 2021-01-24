package com.example.pocketbook.data.network.api

import com.example.pocketbook.data.network.model.ImageCollectionModel
import retrofit2.Call
import retrofit2.http.GET

interface ImageCollectionApi {
    @GET("data/ImageCollections")
    fun getUrls(): Call<List<ImageCollectionModel>>
}