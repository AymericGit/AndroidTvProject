package fr.supinternet.androidtv.presenter

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import fr.supinternet.androidtv.CardViewHolder
import fr.supinternet.androidtv.data.network.model.Movie

class MyPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        return CardViewHolder(ImageCardView(parent?.context));
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val movie = item as Movie
        val holder = viewHolder as CardViewHolder
        val img = holder.card
        img.titleText = movie.name
        img.contentText = movie.rating.toString()
        movie.posterURL?.let { holder.loadImage(it) }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }

}