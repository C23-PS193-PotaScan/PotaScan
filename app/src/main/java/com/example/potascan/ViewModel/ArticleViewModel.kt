package com.example.potascan.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.potascan.data.local.RepositoryArticle
import com.example.potascan.data.UserModel
import com.example.potascan.data.remote.article.DataItem

class ArticleViewModel(private val repo: RepositoryArticle) : ViewModel() {

    fun getUser(): LiveData<UserModel> = repo.getUser()
    fun getAllStories(token: String) = repo.getAllArticle(token)
}