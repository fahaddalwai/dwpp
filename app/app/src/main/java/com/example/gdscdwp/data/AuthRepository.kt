package com.example.gdscdwp.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gdscdwp.network.AuthApiService
import com.example.gdscdwp.network.request.UserSignupInfo
import com.example.gdscdwp.network.dto.User
import com.example.gdscdwp.network.dto.UserX
import com.example.gdscdwp.network.request.UserLoginInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


// Note: This is at the top level of the file, outside of any classes.
private val Context.dataStore by preferencesDataStore("user")

class AuthRepository (private val service: AuthApiService,private val context: Context){

    suspend fun createUser(user: UserSignupInfo): User {
        return service.createUser(user)
    }


    suspend fun loginUser(user:UserLoginInfo):User{
        return service.loginUser(user)
    }
    suspend fun saveToDataStore(userid: String) {
        context.dataStore.edit {
            it[USER_ID] = userid
        }
    }




    val getFromDataStore: Flow<String> = context.dataStore.data
        .map { userDetail ->
            val uiMode = userDetail[USER_ID] ?:"empty"
            uiMode
        }

    suspend fun clearDataStore(){
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun getCurrentUser(): UserX {
        Log.i("datastore value",getFromDataStore.first())
        return service.getUserById(getFromDataStore.first())
    }

    companion object{
        val USER_ID = stringPreferencesKey("USER_ID")

    }
}