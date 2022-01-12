package com.example.gdscdwp.editprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdscdwp.data.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditProfileViewModel(private val repository: AuthRepository): ViewModel() {


    private val _showDialogDelete= MutableLiveData<Boolean>()
    val showDialogDelete: LiveData<Boolean>
        get() = _showDialogDelete

    private val _showDialogUpdate= MutableLiveData<Boolean>()
    val showDialogUpdate: LiveData<Boolean>
        get() = _showDialogUpdate


    private val _eventMainActivity= MutableLiveData<Boolean>()
    val eventMainActivity: LiveData<Boolean>
        get() = _eventMainActivity

    fun setMainActivityFalse(){
        _eventMainActivity.value=false
    }

    fun setMainActivityTrue(){
        _eventMainActivity.value=true
    }


    fun setShowDialogDeleteToFalse(){
        _showDialogDelete.value=false
    }

    fun setShowDialogDeleteToTrue(){
        _showDialogDelete.value=true
    }

    fun setShowDialogUpdateToFalse(){
        _showDialogDelete.value=false
    }

    fun setShowDialogUpdateToTrue(){
        _showDialogDelete.value=true
    }

    fun deleteUser(){
        viewModelScope.launch{
            try {
                repository.deleteUserById()
                repository.clearDataStore()
                Log.i("datastore value",repository.getFromDataStore.first())
                setMainActivityTrue()
            }catch (e: Exception){
                Log.i("error",e.toString())
            }
        }
    }


init{
    setShowDialogDeleteToFalse()
    setShowDialogUpdateToFalse()
    setMainActivityFalse()
}

}