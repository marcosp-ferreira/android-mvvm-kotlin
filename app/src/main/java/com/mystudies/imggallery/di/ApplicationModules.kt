package com.mystudies.imggallery.di

import com.bumptech.glide.Glide
import com.mystudies.imggallery.repository.WebServiceManager
import com.mystudies.imggallery.repository.remote.GalleryRepository
import com.mystudies.imggallery.repository.remote.ImageRepository
import com.mystudies.imggallery.ui.GalleryItemViewModel
import com.mystudies.imggallery.ui.GalleryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModules = module {
    single { WebServiceManager.getInstance() }
    single { GalleryRepository(get()) }
    viewModel { GalleryViewModel(get()) }

    single { Glide.with(androidContext()) }
    single { ImageRepository(get()) }
    viewModel { GalleryItemViewModel(get()) }
}
