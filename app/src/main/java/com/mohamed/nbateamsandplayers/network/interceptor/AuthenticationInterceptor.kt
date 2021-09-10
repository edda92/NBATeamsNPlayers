package com.mohamed.nbateamsandplayers.network.interceptor

import android.os.Build
import com.mohamed.nbateamsandplayers.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Adding authentication token HOST and KEY to the header
        val newRequest = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", BuildConfig.RAPIDAPI_HOST)
            .addHeader("x-rapidapi-key", BuildConfig.RAPIDAPI_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}