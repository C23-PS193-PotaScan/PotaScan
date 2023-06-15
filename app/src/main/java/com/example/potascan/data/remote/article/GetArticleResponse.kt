package com.example.potascan.data.remote.article

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetArticleResponse(

    @field:SerializedName("data")
    val listArticleData: List<DataItem>,
    @field:SerializedName("statusCode")
    val statusCode: Int,
    @field:SerializedName("message")
    val message: String
)

@Parcelize
data class DataItem(

    @field:SerializedName("articleId")
    val articleId: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("category")
    val category: String,
    @field:SerializedName("date")
    val date: String,
    @field:SerializedName("writer")
    val writer: String,
    @field:SerializedName("mainContent")
    val mainContent: String,
    @field:SerializedName("image")
    val imageUrlArticle: String
) : Parcelable
