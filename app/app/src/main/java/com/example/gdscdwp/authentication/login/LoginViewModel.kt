package com.example.gdscdwp.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _goBack = MutableLiveData<Boolean>()
    val goBack: LiveData<Boolean>
        get() = _goBack

    fun setEventGoBackToFalse() {
        _goBack.value = false
    }

    fun setEventGoBackToTrue() {
        _goBack.value = true
    }
    init{
        _goBack.value=false
    }

}