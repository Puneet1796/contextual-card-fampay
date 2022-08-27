package com.puneet.contextualcards.model

import com.google.gson.annotations.SerializedName

data class Gradient(
    @SerializedName("colors") val colors: List<String>,
    @SerializedName("angle") val angle: Int? = 0
)
