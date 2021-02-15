package com.example.pocketbook.data.network.model

import com.google.gson.annotations.SerializedName

data class BookAuthorModel(
    @SerializedName("author")
    val bookAuthor: String,
    @SerializedName("biography")
    val authorBiography: String,
    @SerializedName("photo_url")
    val bookAuthorImageUrl: String
)
