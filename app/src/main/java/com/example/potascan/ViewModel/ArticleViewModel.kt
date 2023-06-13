package com.example.potascan.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.potascan.data.local.RepositoryArticle
import com.example.potascan.data.UserModel
import com.example.potascan.data.remote.article.DataItem

class ArticleViewModel (private val repo :RepositoryArticle):ViewModel() {
    private val _listArticle = MutableLiveData<List<DataItem>>()
    val listArticle: LiveData<List<DataItem>> = _listArticle

//    val listUser: LiveData<List<DataItem>> = _listUser
    fun getUser(): LiveData<UserModel> = repo.getUser()

    suspend fun logout() = repo.logout()



    fun getAllStories(token:String,title: String, image: String, mainContent: String, category: String) = repo.getAllArticle(token,title, image, mainContent, category)
}