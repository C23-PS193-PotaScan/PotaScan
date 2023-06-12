package com.example.potascan.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.potascan.data.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    companion object PreferencesKeys {
        @Volatile
        private var INSTANCE: UserPreference? = null

        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
        val TOKEN = stringPreferencesKey("token")
        val STATE = booleanPreferencesKey("state")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[NAME] ?:"",
                preferences[EMAIL] ?:"",
                preferences[PASSWORD] ?:"",
                preferences[TOKEN] ?: "",
                preferences[STATE] ?: false
            )
        }
    }

    suspend fun loginState(state: Boolean) {
        dataStore.edit { preferences ->
            preferences[STATE] = state
            if (!state){
                preferences[TOKEN] = ""
            }
        }
    }
    suspend fun setToken(token: String){
        dataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun clearUser() {
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun getToken(): String? {
        return dataStore.data.map {
            it[TOKEN]
        }.first()
    }
}