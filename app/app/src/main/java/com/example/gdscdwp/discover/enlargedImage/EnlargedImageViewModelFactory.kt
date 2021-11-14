package com.example.gdscdwp.discover.enlargedImage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class EnlargedImageViewModelFactory(
    private val catUrl: String,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnlargedImageViewModel::class.java)) {
            return EnlargedImageViewModel(catUrl, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}