package com.mystudies.imggallery.ui

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mystudies.imggallery.repository.remote.ImageRepository

class GalleryItemViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    private val _loadedImage = MutableLiveData<Bitmap>()

    public val loadedImage: LiveData<Bitmap> get() = _loadedImage

    private var image: Bitmap? = null

    public suspend fun start(imageUrl: String) {
        val result = imageRepository.loadImageByUrl(imageUrl)
        result.value?.let {
            image = it
        }

        image?.let {
            _loadedImage.postValue(it)
        }
    }

}