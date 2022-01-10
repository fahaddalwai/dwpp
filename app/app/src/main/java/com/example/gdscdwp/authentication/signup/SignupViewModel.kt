package com.example.gdscdwp.authentication.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdscdwp.authentication.isValidEmail
import com.example.gdscdwp.data.AuthRepository
import com.example.gdscdwp.network.request.UserSignupInfo
import kotlinx.coroutines.launch

class SignupViewModel(private val repository: AuthRepository): ViewModel() {

    private val _goBack = MutableLiveData<Boolean>()
    val goBack: LiveData<Boolean>
        get() = _goBack

    private val _eventMainActivity = MutableLiveData<Boolean>()
    val eventMainActivity: LiveData<Boolean>
        get() = _eventMainActivity

    private val _eventSignin = MutableLiveData<Boolean>()
    val eventSignin: LiveData<Boolean>
        get() = _eventSignin

    val username = MutableLiveData<String>()

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val repeatedPassword = MutableLiveData<String>()



    fun setEventMainActivityTrue(){
        _eventMainActivity.value=true
    }

    fun setEventMainActivityFalse(){
        _eventMainActivity.value=false
    }



    fun setEventGoBackToFalse() {
        _goBack.value = false
    }

    fun setEventSigninToTrue(){
        _eventSignin.value=true
    }


    fun eventSignInUser(){
        viewModelScope.launch {
            signIn()
        }
    }

    fun setEventSigninToFalse(){
        _eventSignin.value=false
    }

    fun setEventGoBackToTrue() {
        _goBack.value = true

    }

    suspend fun signIn(){
        if(validateFields()){
            val currentUser= UserSignupInfo(username.value.toString().trim(),
                    email.value.toString().trim(),
                    password.value.toString().trim())
            Log.i("the current user details",currentUser.toString())
            try {
                val user=repository.createUser(currentUser)
                repository.saveToDataStore(user.user._id)
                setEventMainActivityTrue()

            }catch (e: Exception){
                Log.i("error",e.toString())

            }
        }
    }

    fun validateFields():Boolean{
        var isValid=false
        if(email.value.toString().trim().isValidEmail()){
            isValid=true
        }else if(password.value.toString().trim()==repeatedPassword.value.toString().trim()){
            isValid=true
        }

    return isValid
    }



    init{
        setEventMainActivityFalse()
        setEventGoBackToFalse()
    }

}