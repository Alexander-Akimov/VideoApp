package com.use.dagger.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    val id: Int = 0,

    @SerializedName("username")
    @Expose
    val userName: String = "",

    @SerializedName("email")
    @Expose
    val email: String = "",

    @SerializedName("website")
    @Expose
    val website: String = ""
) {

}