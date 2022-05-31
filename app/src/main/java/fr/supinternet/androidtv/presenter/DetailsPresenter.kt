package fr.supinternet.androidtv.presenter

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import fr.supinternet.androidtv.data.network.model.Movie
import fr.supinternet.androidtv.fragment.DetailsFragment

class DetailsPresenter() : AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(viewHolder: ViewHolder?, item: Any?) {
        val movie: Movie? = item as Movie
        if (movie != null) {
            viewHolder?.apply {
                title.text = movie.name
                var actors : String = ""
                movie.actors?.forEach {
                    actors += "$it,"
                }
                subtitle.text = actors
                body.text = movie.overview
            }

        }
    }
}