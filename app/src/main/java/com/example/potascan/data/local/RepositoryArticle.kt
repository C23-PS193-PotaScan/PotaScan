package com.example.potascan.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.potascan.api.article.ApiServiceArticle
import com.example.potascan.api.scan.ApiService
import com.example.potascan.data.LoginResponse
import com.example.potascan.data.Result
import com.example.potascan.data.remote.RegisterResponse
import com.example.potascan.data.remote.article.GetArticleResponse
import com.example.potascan.data.remote.scan.PostModelResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.HttpException

class RepositoryArticle(
    private val api: ApiServiceArticle,
    private val pref: UserPreference
) {


    companion object {
        @Volatile
        private var instance: RepositoryArticle? = null
        fun getInstance(
            apiService: ApiServiceArticle, pref: UserPreference
        ): RepositoryArticle = instance ?: synchronized(this) {
            instance ?: RepositoryArticle(apiService, pref)
        }.also { instance = it }
    }


    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword : String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val result = api.register(name, email, password,confirmPassword)
            val returnedResponse: LiveData<RegisterResponse> = MutableLiveData(result)
            withContext(Dispatchers.Main) {
                emitSource(returnedResponse.map { Result.Success(it) })
            }
        } catch (e: HttpException) {
            val jsonString = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(jsonString, RegisterResponse::class.java)
            val message = errorResponse.message
            emit(Result.Error(message))
        }
    }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val result = api.login(email, password)
            if (result.message == "Success") {
                pref.setToken(result.data.accessToken)
                pref.loginState(true)
                val returnedResponse: LiveData<LoginResponse> = MutableLiveData(result)
                withContext(Dispatchers.Main) {
                    emitSource(returnedResponse.map { Result.Success(it) })
                }
            } else {
                emit(Result.Error(result.message))
            }
        } catch (e: HttpException) {
            val jsonString = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(jsonString, LoginResponse::class.java)
            val message = errorResponse.message
            emit(Result.Error(message))
        }
    }
}