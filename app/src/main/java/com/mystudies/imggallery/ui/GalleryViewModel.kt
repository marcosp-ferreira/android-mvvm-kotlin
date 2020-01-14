package com.mystudies.imggallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mystudies.imggallery.model.ImageModel
import com.mystudies.imggallery.model.ItemGalleryModel
import com.mystudies.imggallery.repository.remote.GalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GalleryViewModel(private val galleryRepository: GalleryRepository) : ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()
    private val _loadedContent = MutableLiveData<List<ImageModel>>()
    private var result: List<ImageModel> = ArrayList<ImageModel>()

    public val showLoading: LiveData<Boolean> get() = _showLoading
    public val loadedContent: LiveData<List<ImageModel>> get() = _loadedContent

    public suspend fun start() {
        _showLoading.value = true
        withContext(Dispatchers.IO) {
            galleryRepository.fetchImages(SEARCH_CATS).value?.let {
                result = prepareResultsToBeDisplayed(it)
            }
        }
        _loadedContent.value = result
        _showLoading.value = false
    }

    private suspend fun prepareResultsToBeDisplayed(items: List<ItemGalleryModel>) : List<ImageModel> {
        val result = ArrayList<ImageModel>()

        for (itemGalleryModel in items) {
            itemGalleryModel.images?.let {
                for (image: ImageModel in itemGalleryModel.images) {
                    if (image.type.contains("image") && !image.url.isNullOrEmpty()) {
                        result.add(ImageModel(image.type, getSizedImageUrl(image.url)))
                    }
                }
            }
        }

        return result.distinct()
    }

    private fun getSizedImageUrl(oldUrl: String) : String {
        return try {
            val oldSplitUrl = oldUrl.split("/") as ArrayList
            val subOldSplitUrl = oldSplitUrl.subList(0, oldSplitUrl.size - 1)
            val imageName = oldSplitUrl.last().split('.').first() + SMALL_THUMBNAIL
            val imageType = oldSplitUrl.last().split('.').last()
            subOldSplitUrl.add("${imageName}.${imageType}")
            val newUrl = subOldSplitUrl.joinToString("/")
            newUrl
        } catch (e: Exception) {
            oldUrl
        }
    }

    companion object {
        private const val SEARCH_CATS = "cats"
        private const val SMALL_THUMBNAIL = "t"
    }

}