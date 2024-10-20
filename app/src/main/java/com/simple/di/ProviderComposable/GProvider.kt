package com.simple.di.ProviderComposable

import androidx.lifecycle.ViewModel
import com.simple.di.listeners.Listeners
import com.simple.di.storageService.StorageService
import com.simple.services.networkService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GProvider @Inject constructor(
    val networkService: networkService,
    val _StorageService: StorageService,
    val Listeners: Listeners
) : ViewModel()


