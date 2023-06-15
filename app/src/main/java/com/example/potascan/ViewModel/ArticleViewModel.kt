package com.example.potascan.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.potascan.data.local.RepositoryArticle
import com.example.potascan.data.UserModel

class ArticleViewModel(private val repo: RepositoryArticle) : ViewModel() {

    fun getUser(): LiveData<UserModel> = repo.getUser()
    fun getAllArticle(token: String) = repo.getAllArticle(token)
}