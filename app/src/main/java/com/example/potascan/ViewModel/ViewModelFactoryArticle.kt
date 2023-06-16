package com.example.potascan.ViewModel
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.potascan.data.ArticleInjection
import com.example.potascan.data.local.RepositoryArticle

class ViewModelFactoryArticle(private val repo: RepositoryArticle) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repo) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repo) as T
            }
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(repo) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion   object {
        @Volatile
        private var INSTANCE: ViewModelFactoryArticle? = null

        @JvmStatic
        fun getInstance(customPref: DataStore<Preferences>): ViewModelFactoryArticle {
            return INSTANCE ?: synchronized(this) {
                val instance = ViewModelFactoryArticle(
                    ArticleInjection.provideRepositoryArticle(customPref)
                )
                INSTANCE = instance
                instance
            }
        }

    }
}