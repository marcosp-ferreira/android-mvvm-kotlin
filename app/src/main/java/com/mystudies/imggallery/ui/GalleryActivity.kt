package com.mystudies.imggallery.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.mystudies.imggallery.R
import com.mystudies.imggallery.model.ImageModel
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class GalleryActivity : AppCompatActivity() {

    private val galleryViewModel: GalleryViewModel by inject()

    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gallery)

        init()
    }

    private fun init() {
        lifecycleScope.launch {
            galleryViewModel.start()
        }
        addObservables()
    }

    private fun addObservables() {
        galleryViewModel.showLoading.observe(this, Observer<Boolean> {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        galleryViewModel.loadedContent.observe(this, Observer<List<ImageModel>> {
            loadContentOnScreen(it)
        })
    }

    private fun loadContentOnScreen(items: List<ImageModel>) {
        galleryRecyclerView.layoutManager = GridLayoutManager(this, 4)
        galleryAdapter = GalleryAdapter(items)
        galleryRecyclerView.setHasFixedSize(true)
        galleryRecyclerView.setItemViewCacheSize(4)
        galleryRecyclerView.adapter = galleryAdapter
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        galleryRecyclerView.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
        galleryRecyclerView.visibility = View.VISIBLE
    }

}