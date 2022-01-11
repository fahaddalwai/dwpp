package com.example.gdscdwp.authentication.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdscdwp.authentication.isValidEmail
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.network.request.UserLoginInfo
import com.example.gdscdwp.network.request.UserSignupInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository): ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _eventMainActivity = MutableLiveData<Boolean>()
    val eventMainActivity: LiveData<Boolean>
        get() = _eventMainActivity



    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin


    private val _goBack = MutableLiveData<Boolean>()
    val goBack: LiveData<Boolean>
        get() = _goBack

    fun setEventGoBackToFalse() {
        _goBack.value = false
    }

    fun setEventGoBackToTrue() {
        _goBack.value = true
    }


    fun setEventMainActivityTrue(){
        _eventMainActivity.value=true
    }

    fun setEventMainActivityFalse(){
        _eventMainActivity.value=false
    }

    fun setEventLoginTrue(){
        _eventLogin.value=true
    }

    fun setEventLoginFalse(){
        _eventLogin.value=false
    }

    fun eventLoginInUser(){
        viewModelScope.launch {
            login()
        }
    }


    suspend fun login(){
        //if(email.value.toString().trim().isValidEmail()){
            val currentUser= UserLoginInfo(email.value.toString().trim(),
                password.value.toString().trim())
            Log.i("the current user details",currentUser.toString())
            try {
                val user=repository.loginUser(currentUser)
                repository.saveToDataStore(user.user._id)
                setEventMainActivityTrue()

            }catch (e: Exception){
                Log.i("error",e.toString())

            }
       // }
    }



    init{
        setEventMainActivityFalse()
        setEventGoBackToFalse()

    }

}