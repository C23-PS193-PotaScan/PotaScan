package com.example.potascan.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.potascan.data.UserModel
import com.example.potascan.data.local.RepositoryArticle
import com.example.potascan.data.local.UserPreference
import com.example.potascan.data.remote.article.DataItem

class MainViewModel(private val repo: RepositoryArticle) : ViewModel() {

    suspend fun logout() = repo.logout()

}