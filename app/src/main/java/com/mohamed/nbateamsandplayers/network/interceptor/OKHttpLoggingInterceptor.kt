package com.mohamed.nbateamsandplayers.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class OKHttpLoggingInterceptor: Interceptor{

    var TAG = OKHttpLoggingInterceptor::class.java.simpleName

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(TAG, String.format("Sending request %s on %s%n%s",
            request.url(), chain.connection(), request.headers()))

        val response = chain.proceed(request)

        Log.d(TAG, java.lang.String.format(
            "Received response for %s %n%s with %s",
            response.request().url(), response.headers(), response.body().toString()
        ))

        return response
    }

}