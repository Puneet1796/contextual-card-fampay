package com.puneet.contextualcards.model

import androidx.recyclerview.widget.DiffUtil
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

// `id` property is needed for comparison for the adapter
// though it's not one of the mentioned properties in the assignment
data class CardGroup(
    @SerializedName("id") val id: Int,
    @SerializedName("design_type") val designType: DesignType,
    @SerializedName("name") val name: String,
    @SerializedName("cards") val cards: List<Card>,
    @SerializedName("height") val height: Int,
    @SerializedName("is_scrollable") val isScrollable: Boolean
) {
    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<CardGroup>() {
            override fun areItemsTheSame(oldItem: CardGroup, newItem: CardGroup): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CardGroup, newItem: CardGroup): Boolean {
                return oldItem == newItem
            }
        }
    }
}
