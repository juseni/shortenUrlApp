package com.example.data.platform

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url
            .newBuilder()
            .build()
        request = request
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}