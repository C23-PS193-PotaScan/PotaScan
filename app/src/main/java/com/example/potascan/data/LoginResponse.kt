package com.example.potascan.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
): Parcelable

@Parcelize
data class Data(

	@field:SerializedName("accessToken")
	val accessToken: String
): Parcelable

@Parcelize
data class UserModel(
	val name: String,
	val email: String,
	val password: String,
	val token: String,
	val isLogin: Boolean
) : Parcelable