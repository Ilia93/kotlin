package com.example.pocketbook.data

import com.example.pocketbook.data.network.api.BookApi
import com.example.pocketbook.data.network.api.CommentsApi
import com.example.pocketbook.data.network.api.ImageCollectionApi
import com.example.pocketbook.data.network.api.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkClient {
    private var retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.backendless.com/43478742-9746-20E5-FFE9-BCF4EF766800/AC1DB3CD-C562-4909-BAFB-732BB9688029/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

    fun buildUserApiClient(): UserApi {
        val create: UserApi = retrofit.create(UserApi::class.java)
        return create
    }

    fun buildBookApiClient(): BookApi {
        val create = retrofit.create(BookApi::class.java)
        return create
    }

    fun buildCommentsApiClient(): CommentsApi {
        val create: CommentsApi = retrofit.create(
            CommentsApi::class.java
        )
        return create
    }

    fun buildImageCollectionClient(): ImageCollectionApi {
        val create: ImageCollectionApi = retrofit.create(ImageCollectionApi::class.java)
        return create
    }

    fun <T> buildRetrofitClient(clazz: Class<T>?): T {
        return retrofit.create(clazz)
    }
}