package com.use.dagger.network.auth

import com.use.dagger.models.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Flowable<User>
}