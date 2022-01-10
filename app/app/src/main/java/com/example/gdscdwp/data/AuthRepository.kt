package com.example.gdscdwp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gdscdwp.network.AuthApiService
import com.example.gdscdwp.network.CatApiService
import com.example.gdscdwp.network.UserInfo
import com.example.gdscdwp.network.dto.User
import com.example.gdscdwp.userPreferences.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


// Note: This is at the top level of the file, outside of any classes.
private val Context.dataStore by preferencesDataStore("user")

class AuthRepository (private val service: AuthApiService,private val context: Context){

    suspend fun createUser(user: UserInfo): User {
        return service.createUser(user)
    }

    suspend fun saveToDataStore(userid: String) {
        context.dataStore.edit {
            it[UserPreference.USER_ID] = userid
        }
    }

    //suspend fun getFromDataStore() = context.dataStore.data.first()

    val getFromDataStore: Flow<String> = context.dataStore.data
        .map { userDetail ->
            val uiMode = userDetail[USER_ID] ?:"empty"
            uiMode
        }


    companion object{
        val USER_ID = stringPreferencesKey("USER_ID")

    }
}