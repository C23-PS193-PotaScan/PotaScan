package com.example.potascan.data.remote.scan

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostModelResponse(

	@field:SerializedName("prediction")
	val response: String,

	@field:SerializedName("msg")
	val message: String
) : Parcelable