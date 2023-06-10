package com.example.potascan.api

import com.example.potascan.data.remote.PostModelResponse
import com.example.potascan.data.remote.UploadModelResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("upload")
    suspend fun uploadPhoto(
        @Part photo: MultipartBody.Part
    ): PostModelResponse
}