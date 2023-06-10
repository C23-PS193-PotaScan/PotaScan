package com.example.potascan.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.potascan.api.ApiService
import com.example.potascan.data.Result
import com.example.potascan.data.remote.PostModelResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.HttpException

class Repository(private val api: ApiService) {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(apiService: ApiService): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService)
            }.also { instance = it }
    }

    fun postPhoto(image: MultipartBody.Part): LiveData<Result<PostModelResponse>> = liveData {
        emit(Result.Loading)
        try {
            val result = api.uploadPhoto(image)
            val returnedResponse: LiveData<PostModelResponse> = MutableLiveData(result)
            withContext(Dispatchers.Main) {
                emitSource(returnedResponse.map { Result.Success(it) })
            }
        } catch (e: HttpException) {
            val jsonString = e.response()?.errorBody()?.string()
            if (jsonString != null) {
                try {
                    val errorResponse = Gson().fromJson(jsonString, PostModelResponse::class.java)
                    val message = errorResponse.message
                    emit(Result.Error(message))
                } catch (ex: Exception) {
                    emit(Result.Error("Failed to parse error response"))
                }
            } else {
                emit(Result.Error("Unknown error"))
            }
        }
    }
}