package com.puneet.contextualcards.model

import com.google.gson.annotations.SerializedName

data class Entity(
    @SerializedName("text") val text: String,
    @SerializedName("color") val color: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("font_style") val fontStyle: String?
)
