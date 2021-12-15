package com.ravish.android.uitesting.withmockapi.data.network

import com.ravish.android.uitesting.withmockapi.data.model.User
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiServices {

    @GET("/users/<github-username>")
    suspend fun getUser(): User

    companion object Factory {
        fun create(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }
}