package com.mystudies.imggallery.repository.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mystudies.imggallery.model.ItemGalleryModel
import com.mystudies.imggallery.repository.API
import org.json.JSONObject
import java.util.ArrayList

class GalleryRepository(private val api: API) {

    public fun fetchImages(imageName: String) : List<ItemGalleryModel> {
        var result = ArrayList<ItemGalleryModel>()
        val response = api.fetchImages(imageName).execute()

        response?.body()?.let { it ->
            val jsonBody = JSONObject(it.string())
            jsonBody.getString("data").let {
                result = Gson().fromJson<ArrayList<ItemGalleryModel>>(it)
            }
        }

        response?.errorBody()?.let {
           // TODO: To be implemented
        }

        return result
    }

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

}
