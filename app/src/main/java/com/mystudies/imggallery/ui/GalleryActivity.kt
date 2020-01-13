package com.mystudies.imggallery.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mystudies.imggallery.R
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class GalleryActivity : AppCompatActivity() {

    private val galleryViewModel: GalleryViewModel by inject()

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
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

}