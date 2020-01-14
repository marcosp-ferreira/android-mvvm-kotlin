package com.mystudies.imggallery.ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mystudies.imggallery.R
import com.mystudies.imggallery.model.ImageModel
import kotlinx.android.synthetic.main.item_gallery.view.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class GalleryAdapter(private val items: List<ImageModel>) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.start(items[position])
    }

    override fun onViewAttachedToWindow(holder: ItemViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.loadContents()
    }

    override fun onViewDetachedFromWindow(holder: ItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unloadContent()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var itemModel: ImageModel

        private val galleryItemViewModel: GalleryItemViewModel by (itemView.context as AppCompatActivity).inject()
        private var job = SupervisorJob()
        private val uiScope = CoroutineScope(Dispatchers.Main + job)

        public fun start(itemGalleryModel: ImageModel) {
            itemModel = itemGalleryModel
        }

        public fun unloadContent() {
            uiScope.coroutineContext.cancelChildren()
            galleryItemViewModel.loadedImage.removeObservers(itemView.context as AppCompatActivity)
            itemView.imageGalleryItem.setImageBitmap(null)
        }

        public fun loadContents() {
            galleryItemViewModel.loadedImage.observe(itemView.context as AppCompatActivity, Observer<Bitmap> {
                itemView.imageGalleryItem.setImageBitmap(it)
            })

            loadImage()
        }

        private fun loadImage() = uiScope.launch {
            withContext(Dispatchers.IO) {
                itemModel.url?.let {
                    galleryItemViewModel.start(it)
                }
            }
        }

    }

}