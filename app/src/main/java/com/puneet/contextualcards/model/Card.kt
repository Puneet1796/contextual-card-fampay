package com.puneet.contextualcards.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("name") val name: String,
    @SerializedName("formatted_title") val formattedTitle: FormattedText?,
    @SerializedName("title") val title: String?,
    @SerializedName("formatted_description") val formattedDescription: FormattedText?,
    @SerializedName("description") val description: String?,
    @SerializedName("icon") val icon: CardImage?,
    @SerializedName("url") val url: String?,
    @SerializedName("bg_image") val bgImage: CardImage?,
    @SerializedName("bg_color") val bgColor: String?,
    @SerializedName("bg_gradient") val bgGradient: Gradient?,
    @SerializedName("cta") val cta: List<CallToAction>
) {
    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem == newItem
            }
        }
    }
}
