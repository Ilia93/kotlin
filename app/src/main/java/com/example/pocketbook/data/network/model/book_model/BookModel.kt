package com.example.pocketbook.data.network.model.book_model

import com.google.gson.annotations.SerializedName
import java.util.*

data class BookModel(
    @SerializedName("age_limit")
    val ageLimit: String,
    @SerializedName("author")
    val bookAuthor: String,
    @SerializedName("duration")
    val bookDuration: String,
    @SerializedName("language")
    val bookLanguage: String,
    @SerializedName("annotation")
    val bookAnnotation: String,
    @SerializedName("name")
    val bookName: String,
    @SerializedName("rating")
    val bookRating: Int,
    @SerializedName("reader")
    val bookReader: String,
    @SerializedName("series")
    val bookSeries: String,
    @SerializedName("series_order_tag")
    val seriesOrderTag: String,
    @SerializedName("size")
    val bookSize: String,
    @SerializedName("style")
    val bookStyle: String,
    @SerializedName("type")
    val bookType: String,
    @SerializedName("date")
    val dateOfBooksAdd: Date,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_finished_flag")
    val isBookFinished: Boolean,
    @SerializedName("followers")
    val readersCount: Int,
    @SerializedName("subscribe_type")
    val typeOfBookSubscribe: String,
    @SerializedName("isRated")
    val isRated: Boolean,
    @SerializedName("marksCount")
    val marksCount: Int,
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("marksSum")
    val marksSum: Int
)