package com.example.potascan.api.article

import com.example.potascan.data.LoginResponse
import com.example.potascan.data.remote.RegisterResponse
import com.example.potascan.data.remote.article.GetArticleResponse
import retrofit2.http.*

interface ApiServiceArticle {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPass") confirmPass: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse


    @GET("article")
    suspend fun getAllArticle(
        @Header("Authorization") token: String
    ): GetArticleResponse

}