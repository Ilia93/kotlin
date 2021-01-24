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
            .baseUrl("https://api.backendless.com/204A222F-6DEE-D154-FF8F-539C88D83400/5E09FB45-952E-47FC-8835-1F40B5F790B4/")
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