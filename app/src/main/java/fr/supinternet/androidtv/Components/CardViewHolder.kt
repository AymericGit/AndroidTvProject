package fr.supinternet.androidtv

import android.view.View
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide

class CardViewHolder(view: View) : Presenter.ViewHolder(view) {

    val card: ImageCardView = view as ImageCardView

    init {
        card.setMainImageDimensions(view.resources.getDimensionPixelSize(R.dimen.card_width),
            view.resources.getDimensionPixelSize(R.dimen.card_height))
    }

    fun loadImage(url: String) {
        Glide.with(view)
            .load(url)
            .into(card.mainImageView)
    }


}
