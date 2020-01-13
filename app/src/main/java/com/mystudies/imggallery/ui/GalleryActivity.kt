package com.mystudies.imggallery.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.mystudies.imggallery.R
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gallery)
    }

    private fun initScreen() {
        showLoading()
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

}