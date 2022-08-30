package com.puneet.contextualcards.model

import com.google.gson.annotations.SerializedName

enum class FontStyle {
    @SerializedName("underline")
    UNDERLINE,

    @SerializedName("italic")
    ITALIC
}

data class Entity(
    @SerializedName("text") val text: String,
    @SerializedName("color") val color: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("font_style") val fontStyle: FontStyle?
)
