package com.ravish.android.uitesting.withmockapi.data.network

import com.ravish.android.uitesting.withmockapi.data.model.UsersData
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiServices {

    @GET("/v1/user")
    suspend fun getUser(): UsersData

    companion object Factory {
        fun create(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }
}