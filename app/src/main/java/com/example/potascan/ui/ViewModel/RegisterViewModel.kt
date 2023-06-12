package com.example.potascan.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.potascan.data.local.RepositoryArticle

class RegisterViewModel (private val repo: RepositoryArticle) : ViewModel() {
    fun register(name: String, email: String, password: String, confirmPass : String) = repo.register(name, email, password, confirmPass)
}
