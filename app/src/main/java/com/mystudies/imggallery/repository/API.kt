package com.mystudies.imggallery.repository

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("3/gallery/search")
    public fun fetchImages(@Query("q") imgName: String) : Call<ResponseBody>
}