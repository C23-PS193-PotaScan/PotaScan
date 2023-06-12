package com.example.potascan.api

import com.example.potascan.api.article.ApiServiceArticle
import com.example.potascan.api.scan.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS) // Increase the connection timeout
                .readTimeout(30, TimeUnit.SECONDS)    // Increase the read timeout
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://model-and-classification-api-fbaiidjkha-uc.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
        fun getApiServiceArticle() : ApiServiceArticle{
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS) // Increase the connection timeout
                .readTimeout(30, TimeUnit.SECONDS)    // Increase the read timeout
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://model-and-classification-api-fbaiidjkha-uc.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiServiceArticle::class.java)
        }
    }
}