package com.mystudies.imggallery.model

import com.google.gson.annotations.SerializedName

data class ImageModel(

    @SerializedName("type")
    val type: String,

    @SerializedName("link")
    val url: String?
)