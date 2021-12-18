package com.ravish.android.uitesting.withmockapi.data.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val basic = Credentials.basic("","")

        val builder: Request.Builder = original.newBuilder()
            .header("Authorization", basic)

        val request: Request = builder.build()
        return chain.proceed(request)
    }
}