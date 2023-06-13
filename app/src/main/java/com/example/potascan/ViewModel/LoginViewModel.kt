package com.example.potascan.ViewModel

import androidx.lifecycle.ViewModel
import com.example.potascan.data.local.RepositoryArticle

class LoginViewModel(private val repo: RepositoryArticle) : ViewModel() {
    fun login(email: String, password: String) = repo.login(email, password)
}