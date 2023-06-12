package com.example.potascan.data.remote.scan

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploadModelResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("prediction")
	val prediction: String
):Parcelable
