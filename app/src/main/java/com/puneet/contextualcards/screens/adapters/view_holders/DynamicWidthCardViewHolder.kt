package com.puneet.contextualcards.screens.adapters.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puneet.contextualcards.databinding.LayoutDynamicWidthCardBinding
import com.puneet.contextualcards.model.CardGroup

class DynamicWidthCardViewHolder(
    private val binding: LayoutDynamicWidthCardBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cardGroup: CardGroup) {
        binding.contentTv.text = cardGroup.name
    }

    companion object {
        fun from(parent: ViewGroup): DynamicWidthCardViewHolder =
            DynamicWidthCardViewHolder(
                LayoutDynamicWidthCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
    }
}
