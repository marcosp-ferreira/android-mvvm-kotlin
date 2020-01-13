package com.mystudies.imggallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mystudies.imggallery.repository.remote.GalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GalleryViewModel(private val galleryRepository: GalleryRepository) : ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()

    public val showLoading: LiveData<Boolean> get() = _showLoading

    public suspend fun start() {
        _showLoading.value = true
        withContext(Dispatchers.IO) {
            galleryRepository.fetchImages(SEARCH_CATS)
        }
        _showLoading.value = false
    }

    companion object {
        private const val SEARCH_CATS = "cats"
    }

}