package com.example.data.network.response

import com.example.data.model.ShortenModel
import com.google.gson.annotations.SerializedName

data class ShortenResponse(
    @SerializedName("ok") val status: Boolean,
    @SerializedName("result") val result: ShortenModel? = null
)