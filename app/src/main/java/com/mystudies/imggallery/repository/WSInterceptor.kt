package com.mystudies.imggallery.repository

import okhttp3.Interceptor
import okhttp3.Response

class WSInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID 1ceddedc03a5d71")
                .build()
        )
    }
}