package com.puneet.contextualcards.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puneet.contextualcards.databinding.LayoutSmallDisplayCardItemBinding
import com.puneet.contextualcards.model.Card
import com.puneet.contextualcards.utils.paintCard
import com.puneet.contextualcards.utils.setDrawableAsync
import com.puneet.contextualcards.utils.styledText

class SmallDisplayCardItemAdapter :
    ListAdapter<Card, SmallDisplayCardItemAdapter.CardViewHolder>(Card.CALLBACK) {

    class CardViewHolder(
        private val binding: LayoutSmallDisplayCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            with(binding) {
                root.paintCard(card)
                card.icon?.let { profilePictureIv.setDrawableAsync(it) }
                titleTv.text = card.formattedTitle?.styledText() ?: card.title
                subtitleTv.text = card.formattedDescription?.styledText() ?: card.description
            }
        }

        companion object {
            fun from(parent: ViewGroup): CardViewHolder =
                CardViewHolder(
                    LayoutSmallDisplayCardItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}
