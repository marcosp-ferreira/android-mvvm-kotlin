package com.mystudies.imggallery.model

import com.google.gson.annotations.SerializedName

data class ItemGalleryModel(

    @SerializedName("images")
    val images: ArrayList<ImageModel>?
)