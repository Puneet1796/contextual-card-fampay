package com.puneet.contextualcards.screens.adapters.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puneet.contextualcards.databinding.LayoutSmallDisplayCardBinding
import com.puneet.contextualcards.databinding.LayoutSmallDisplayCardItemBinding
import com.puneet.contextualcards.model.Card
import com.puneet.contextualcards.model.CardGroup
import com.puneet.contextualcards.screens.adapters.SmallDisplayCardItemAdapter
import com.puneet.contextualcards.utils.paintCard
import com.puneet.contextualcards.utils.setDrawableAsync
import com.puneet.contextualcards.utils.styledText

class SmallDisplayCardViewHolder(
    private val binding: LayoutSmallDisplayCardBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cardGroup: CardGroup) {
        if (cardGroup.isScrollable) {
            setupRecyclerView(cardGroup.cards)
        } else {
            displayEquallyWeightedCards(cardGroup.cards)
        }
    }

    private fun setupRecyclerView(cards: List<Card>) {
        with(binding.cardsList) {
            val cardItemAdapter = SmallDisplayCardItemAdapter()
            adapter = cardItemAdapter
            cardItemAdapter.submitList(cards)
        }
    }

    private fun displayEquallyWeightedCards(cards: List<Card>) {
        with(binding.cardsLayout) {
            val card = cards.first()
            val itemBinding = LayoutSmallDisplayCardItemBinding.inflate(
                LayoutInflater.from(context)
            )
            with(itemBinding) {
                root.paintCard(card)
                card.icon?.let { profilePictureIv.setDrawableAsync(it) }
                titleTv.text = card.formattedTitle?.styledText() ?: card.title
                subtitleTv.text = card.formattedDescription?.styledText() ?: card.description
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): SmallDisplayCardViewHolder =
            SmallDisplayCardViewHolder(
                LayoutSmallDisplayCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
    }
}
