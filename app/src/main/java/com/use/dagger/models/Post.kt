package com.use.dagger.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    @Expose
    val userId: Int = 0,

    @SerializedName("id")
    @Expose
    val id: Int = 0,

    @SerializedName("title")
    @Expose
    val title: String = "",

    @SerializedName("body")
    @Expose
    val body: String = ""
)