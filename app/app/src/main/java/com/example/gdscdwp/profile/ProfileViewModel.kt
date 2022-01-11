package com.example.gdscdwp.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.network.dto.User
import com.example.gdscdwp.network.dto.UserX
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: AuthRepository): ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private var _currentUser = MutableLiveData<UserX>()
    val currentUser: LiveData<UserX>
        get() = _currentUser

    fun putUserValue(){
        _userName.value= _currentUser.value?.name
        _email.value= _currentUser.value?.email
    }

    init{
        viewModelScope.launch{
            _currentUser.value=repository.getCurrentUser()

        }
    }
}