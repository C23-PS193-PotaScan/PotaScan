package com.example.potascan.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.potascan.api.ApiConfig
import com.example.potascan.data.local.RepositoryArticle
import com.example.potascan.data.local.UserPreference

object ArticleInjection {
    fun provideRepositoryArticle(datastore : DataStore<Preferences>): RepositoryArticle {
        val apiServiceArticle = ApiConfig.getApiServiceArticle()
        val pref = UserPreference.getInstance(datastore)
        return RepositoryArticle.getInstance(apiServiceArticle,pref)
    }
}