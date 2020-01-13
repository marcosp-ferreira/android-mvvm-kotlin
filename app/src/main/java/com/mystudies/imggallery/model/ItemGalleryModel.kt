package com.mystudies.imggallery.model

import com.google.gson.annotations.SerializedName

data class ItemGalleryModel(

    @SerializedName("ïd")
    val id: String,

    @SerializedName("link")
    val url: String
)