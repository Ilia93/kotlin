package com.example.pocketbook.data.network.model.book_model

import com.google.gson.annotations.SerializedName

data class SelectedBookArgs(
    @SerializedName("age_limit")
    val ageLimit: String,
    @SerializedName("author")
    val bookAuthor: String,
    @SerializedName("language")
    val bookLanguage: String,
    @SerializedName("annotation")
    val bookAnnotation: String,
    @SerializedName("name")
    val bookName: String,
    @SerializedName("rating")
    val bookRating: Int,
    @SerializedName("series")
    val bookSeries: String,
    @SerializedName("style")
    val bookStyle: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_finished_flag")
    val isBookFinished: Boolean,
    @SerializedName("subscribe_type")
    val typeOfBookSubscribe: String,
    @SerializedName("isRated")
    val isRated: Boolean,
    @SerializedName("marksCount")
    val marksCount: String,
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("marksSum")
    val marksSum: Int
)
