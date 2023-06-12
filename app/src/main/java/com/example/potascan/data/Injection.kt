package com.example.potascan.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.potascan.api.ApiConfig
import com.example.potascan.data.local.Repository
import com.example.potascan.data.local.RepositoryArticle
import com.example.potascan.data.local.UserPreference

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}