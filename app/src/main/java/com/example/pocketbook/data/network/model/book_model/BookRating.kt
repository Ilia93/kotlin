package com.example.pocketbook.data.network.model.book_model

import com.google.gson.annotations.SerializedName

data class BookRating(
    @SerializedName("isRated")
    val isRated: Boolean,
    @SerializedName("marksCount")
    val marksCount: Int,
    @SerializedName("marksSum")
    val marksSum: Int,
    @SerializedName("rating")
    val bookRating: Int,
    @SerializedName("objectId")
    val objectId: String
)
