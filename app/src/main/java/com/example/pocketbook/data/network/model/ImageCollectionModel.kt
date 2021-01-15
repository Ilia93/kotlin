package com.example.pocketbook.data.network.model

import com.google.gson.annotations.SerializedName

data class ImageCollectionModel(
    @SerializedName("back_image_url")
    val backGroundImageUrl: String,

    @SerializedName("button_text")
    val buttonText: String,

    @SerializedName("header_text")
    val headerText: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("tab_text")
    val tabText: String,

    @SerializedName("image_text")
    val text: String
)