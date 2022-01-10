package com.example.gdscdwp.userPreferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

// Note: This is at the top level of the file, outside of any classes.
private val Context.dataStore by preferencesDataStore("user")

class UserPreference(
    private val context: Context
) {

    suspend fun savetoDataStore(userid: String) {
        context.dataStore.edit {
            it[USER_ID] = userid
        }
    }

    companion object{
        val USER_ID = stringPreferencesKey("USER_ID")

    }


}