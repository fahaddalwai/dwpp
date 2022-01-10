package com.example.gdscdwp.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.gdscdwp.data.AuthRepository


class LoginViewModelFactory(private val repository: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}