package com.puneet.contextualcards.model

import com.google.gson.annotations.SerializedName

enum class ImageType {
    @SerializedName("asset")
    ASSET,

    @SerializedName("ext")
    EXTERNAL
}

data class CardImage(
    @SerializedName("image_type") val imageType: ImageType,
    @SerializedName("asset_type") val assetType: String?,
    @SerializedName("image_url") val imageUrl: String?
)
