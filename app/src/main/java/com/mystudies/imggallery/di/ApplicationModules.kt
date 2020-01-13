package com.mystudies.imggallery.di

import com.mystudies.imggallery.repository.WebServiceManager
import com.mystudies.imggallery.repository.remote.GalleryRepository
import com.mystudies.imggallery.ui.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModules = module {
    single { WebServiceManager.getInstance() }
    single { GalleryRepository(get()) }
    viewModel { GalleryViewModel(get()) }
}
