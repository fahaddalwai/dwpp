package com.example.gdscdwp.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditProfileViewModel: ViewModel() {


    private val _showDialogDelete= MutableLiveData<Boolean>()
    val showDialogDelete: LiveData<Boolean>
        get() = _showDialogDelete

    private val _showDialogUpdate= MutableLiveData<Boolean>()
    val showDialogUpdate: LiveData<Boolean>
        get() = _showDialogUpdate



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

init{
    setShowDialogDeleteToFalse()
    setShowDialogUpdateToFalse()
}

}