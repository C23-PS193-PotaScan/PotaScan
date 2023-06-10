package com.example.potascan.data

import com.example.potascan.api.ApiConfig
import com.example.potascan.data.local.Repository

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}