package com.puneet.contextualcards.model

import com.google.gson.annotations.SerializedName

enum class DesignType(val value: String) {
    @SerializedName("HC1")
    SMALL_DISPLAY_CARD("HC1"),

    @SerializedName("HC3")
    BIG_DISPLAY_CARD("HC3"),

    @SerializedName("HC5")
    IMAGE_CARD("HC5"),

    @SerializedName("HC6")
    SMALL_CARD_WITH_ARROW("HC6"),

    @SerializedName("HC9")
    DYNAMIC_WIDTH_CARD("HC9")
}

data class CardGroup(
    @SerializedName("design_type") val designType: DesignType,
    @SerializedName("name") val name: String,
    @SerializedName("cards") val cards: List<Card>,
    @SerializedName("height") val height: Int,
    @SerializedName("is_scrollable") val isScrollable: Boolean
)
