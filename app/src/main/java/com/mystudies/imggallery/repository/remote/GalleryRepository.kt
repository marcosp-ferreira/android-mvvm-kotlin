package com.mystudies.imggallery.repository.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mystudies.imggallery.model.ItemGalleryModel
import com.mystudies.imggallery.repository.API
import com.mystudies.imggallery.utils.WSResult
import org.json.JSONObject
import java.lang.Exception
import java.util.ArrayList

class GalleryRepository(private val api: API) {

    public fun fetchImages(imageName: String) : WSResult<List<ItemGalleryModel>> {
        return try {
            var result = WSResult<List<ItemGalleryModel>>(WSResult.Type.ERROR, null)

            val response = api.fetchImages(imageName).execute()
            response?.body()?.let { it ->
                val jsonBody = JSONObject(it.string())
                jsonBody.getString("data").let {
                    val obj = Gson().fromJson<ArrayList<ItemGalleryModel>>(it)
                    result = WSResult<List<ItemGalleryModel>>(WSResult.Type.SUCCESS, obj)
                }
            }
            response?.errorBody()?.let {
                result = WSResult<List<ItemGalleryModel>>(WSResult.Type.ERROR, null)
            }

            result
        } catch (e: Exception) {
            WSResult<List<ItemGalleryModel>>(WSResult.Type.ERROR, null)
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

}
