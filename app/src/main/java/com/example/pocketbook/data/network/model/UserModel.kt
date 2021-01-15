package com.example.pocketbook.data.network.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
     val userId: String,
    @SerializedName("name")
     val userName: String,
    @SerializedName("second_name")
     val userSecondName: String,
    @SerializedName("subscribers")
     val userSubscribe: String,
)