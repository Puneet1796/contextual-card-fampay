package com.puneet.contextualcards.screens.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puneet.contextualcards.model.CardGroup
import com.puneet.contextualcards.model.DesignType
import com.puneet.contextualcards.screens.adapters.view_holders.*

class VerticalCardListAdapter :
    ListAdapter<CardGroup, RecyclerView.ViewHolder>(CardGroup.CALLBACK) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item.designType) {
            DesignType.SMALL_DISPLAY_CARD -> 0
            DesignType.BIG_DISPLAY_CARD -> 1
            DesignType.IMAGE_CARD -> 2
            DesignType.SMALL_CARD_WITH_ARROW -> 3
            DesignType.DYNAMIC_WIDTH_CARD -> 4
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> SmallDisplayCardViewHolder.from(parent)
            1 -> BigDisplayCardViewHolder.from(parent)
            2 -> ImageCardViewHolder.from(parent)
            3 -> SmallCardWithArrowViewHolder.from(parent)
            4 -> DynamicWidthCardViewHolder.from(parent)
            else -> throw IllegalArgumentException("Invalid viewType $viewType")

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SmallDisplayCardViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
            is BigDisplayCardViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
            is ImageCardViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
            is SmallCardWithArrowViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
            is DynamicWidthCardViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
        }
    }
}
