package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ShortenModel(
    @Transient val shortenId: Int,
    @SerializedName("code") val code: String? = null,
    @SerializedName("short_link") val shortLink: String? = null,
    @SerializedName("full_short_link") val fullShortLink: String? = null,
    @SerializedName("short_link2") val shortLink2: String? = null,
    @SerializedName("full_short_link2") val fullShortLink2: String? = null,
    @SerializedName("share_link") val shareLink: String? = null,
    @SerializedName("full_share_link") val fullShareLink: String? = null,
    @SerializedName("original_link") val originalLink: String? = null
)