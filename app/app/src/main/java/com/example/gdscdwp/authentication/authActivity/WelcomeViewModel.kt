package com.example.gdscdwp.authentication.authActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdscdwp.data.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: AuthRepository) : ViewModel() {


    private val _eventSkipAuth = MutableLiveData<Boolean>()
    val eventSkipAuth: LiveData<Boolean>
        get() = _eventSkipAuth

    fun setEventSkipAuthFalse()
    {
        _eventSkipAuth.value=false
    }

    fun setEventSkipAuthTrue()
    {
        _eventSkipAuth.value=true
    }


    init{
        //TODO if logged out the make sure to remove value from datastore
        setEventSkipAuthFalse()
        viewModelScope.launch{
            val id=repository.getFromDataStore.first()
            if(id=="empty"){
                setEventSkipAuthFalse()
            }else{
                setEventSkipAuthTrue()
            }
        }

    }



}