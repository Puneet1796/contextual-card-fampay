package com.puneet.contextualcards.model

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("card_groups") val cardGroups: List<CardGroup>
)
