package com.puneet.contextualcards.utils

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView
import com.puneet.contextualcards.model.*

fun MaterialCardView.paintCard(card: Card) {
    card.bgColor?.let {
        setCardBackgroundColor(Color.parseColor(it))
    }
    card.bgGradient?.let {
        val drawable = GradientDrawable()
        drawable.colors = it.colors.map { colorHex -> Color.parseColor(colorHex) }
            .toIntArray()
        drawable.orientation = getOrientation(it)
        background = drawable
    }
    card.bgImage?.let { setDrawableAsync(it) }
}

fun View.setDrawableAsync(cardImage: CardImage) {
    Glide.with(this)
        .load(
            when (cardImage.imageType) {
                ImageType.ASSET -> cardImage.assetType
                ImageType.EXTERNAL -> cardImage.imageUrl
            }
        )
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                background = resource
                return false
            }
        })
}

private fun getOrientation(gradient: Gradient): GradientDrawable.Orientation {
    return when (gradient.angle) {
        0 -> GradientDrawable.Orientation.LEFT_RIGHT
        45 -> GradientDrawable.Orientation.BL_TR
        90 -> GradientDrawable.Orientation.BOTTOM_TOP
        135 -> GradientDrawable.Orientation.BR_TL
        180 -> GradientDrawable.Orientation.RIGHT_LEFT
        225 -> GradientDrawable.Orientation.TR_BL
        270 -> GradientDrawable.Orientation.TOP_BOTTOM
        315 -> GradientDrawable.Orientation.TL_BR
        else -> GradientDrawable.Orientation.LEFT_RIGHT
    }
}

fun FormattedText.styledText(): SpannableString {
    val spannable = SpannableString(text)
    entities.forEach { entity ->
        val startIndex = spannable.indexOf("{}")
        val endIndex = entity.text.length - 1
        spannable.replaceFirst("\\{}".toRegex(), entity.text)
        entity.color?.let {
            spannable.setSpan(
                ForegroundColorSpan(Color.parseColor(it)),
                startIndex,
                endIndex,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        entity.fontStyle?.let {
            when (it) {
                FontStyle.UNDERLINE -> {
                    spannable.setSpan(
                        UnderlineSpan(),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
                FontStyle.ITALIC -> {
                    spannable.setSpan(
                        StyleSpan(Typeface.ITALIC),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
            }
        }
    }
    return spannable
}
