package com.example.potascan.data.remote.article

import com.google.gson.annotations.SerializedName

data class GetArticleResponse(

	@field:SerializedName("data")
	val listArticleData: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class DataItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("image")
	val imageUrlArticle: String,

	@field:SerializedName("mainContent")
	val mainContent: String,

	@field:SerializedName("articleId")
	val articleId: String,

	@field:SerializedName("writer")
	val writer: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String
)
