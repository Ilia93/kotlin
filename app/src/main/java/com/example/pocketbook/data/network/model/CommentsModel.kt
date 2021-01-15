package com.example.pocketbook.data.network.model

import com.google.gson.annotations.SerializedName

data class CommentsModel(
    @SerializedName("book_id")
     val bookId: String,
    @SerializedName("date")
     val commentDate: String,
    @SerializedName("text")
     val commentText: String,
    @SerializedName("user_id")
     val userId: String,
    @SerializedName("rating")
     val userRating: Int
)