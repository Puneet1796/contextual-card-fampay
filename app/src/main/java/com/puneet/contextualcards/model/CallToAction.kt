package com.puneet.contextualcards.model

import com.google.gson.annotations.SerializedName

data class CallToAction(
    @SerializedName("text") val text: String,
    @SerializedName("bg_color") val bgColor: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("text_color") val textColor: String?
)
