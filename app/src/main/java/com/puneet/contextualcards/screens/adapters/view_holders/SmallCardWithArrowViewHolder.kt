package com.puneet.contextualcards.screens.adapters.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puneet.contextualcards.databinding.LayoutSmallCardWithArrowBinding
import com.puneet.contextualcards.model.CardGroup

class SmallCardWithArrowViewHolder(
    private val binding: LayoutSmallCardWithArrowBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cardGroup: CardGroup) {
        binding.contentTv.text = cardGroup.name
    }

    companion object {
        fun from(parent: ViewGroup): SmallCardWithArrowViewHolder =
            SmallCardWithArrowViewHolder(
                LayoutSmallCardWithArrowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
    }
}
