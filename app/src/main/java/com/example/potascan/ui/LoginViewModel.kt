package com.example.potascan.ui

import androidx.lifecycle.ViewModel
import com.example.potascan.data.local.RepositoryArticle

class LoginViewModel(private val repo: RepositoryArticle) : ViewModel() {
    fun register(name: String, email: String, password: String) = repo.register(name, email, password)
}