package com.example.potascan.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.potascan.data.Injection
import com.example.potascan.data.local.Repository

import okhttp3.MultipartBody

class ImageViewModel(private val repo: Repository): ViewModel()  {

    private val _isLoading = MutableLiveData<Boolean>()
    fun postImage(image: MultipartBody.Part) = repo.postPhoto(image)
}

class ViewModelFactory(private val repo: Repository) : ViewModelProvider.NewInstanceFactory() {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ImageViewModel::class.java) -> {
                ImageViewModel(repo) as T
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