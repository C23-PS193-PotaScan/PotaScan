package com.example.potascan.ui.ViewModel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.potascan.data.Injection
import com.example.potascan.data.local.Repository
import com.example.potascan.data.local.RepositoryArticle
import com.example.potascan.ui.ImageViewModel
import com.example.potascan.ui.LoginViewModel
import com.example.potascan.ui.ViewModelFactory

class ViewModelFactoryArticle(private val repo: RepositoryArticle) : ViewModelProvider.NewInstanceFactory() {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repo) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion   object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                val instance = ViewModelFactory(
                    Injection.provideRepository()
                )
                INSTANCE = instance
                instance
            }
        }

    }
}