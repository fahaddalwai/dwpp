package com.example.gdscdwp.discover

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EnlargedImageViewModel(catUrl: String, application: Application) : ViewModel() {


    private val _selectedCat = MutableLiveData<String>()
    val selectedCat: LiveData<String>
        get() = _selectedCat

    init {
        _selectedCat.value = catUrl
    }

}




