package com.mystudies.imggallery.repository

import com.mystudies.imggallery.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceManager {

    private var api: API? = null

    fun getInstance() : API {
        api = api?.let {
            it
        } ?: run {
            val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(WSInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.GALLERY_BASE_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(API::class.java)
        }

        return api!!
    }

}
