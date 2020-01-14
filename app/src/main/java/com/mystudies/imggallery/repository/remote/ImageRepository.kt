package com.mystudies.imggallery.repository.remote

import android.graphics.Bitmap
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mystudies.imggallery.utils.WSResult
import java.lang.Exception

class ImageRepository(private val glide: RequestManager) {

    public fun loadImageByUrl(url: String) : WSResult<Bitmap> {
        return try {
            val result = glide.asBitmap()
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .submit()
                .get()

            WSResult<Bitmap>(WSResult.Type.SUCCESS, result)
        } catch (e: Exception) {
            WSResult<Bitmap>(WSResult.Type.ERROR, null)
        }
    }

}