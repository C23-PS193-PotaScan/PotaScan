package com.example.potascan.api.scan

import com.example.potascan.data.remote.scan.PostModelResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("upload")
    suspend fun uploadPhoto(
        @Part file: MultipartBody.Part
    ): PostModelResponse
}