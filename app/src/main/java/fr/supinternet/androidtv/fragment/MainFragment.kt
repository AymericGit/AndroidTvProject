package fr.supinternet.androidtv.fragment

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import fr.supinternet.androidtv.presenter.MyPresenter
import fr.supinternet.androidtv.data.network.NetworkManager
import kotlinx.coroutines.*

class MainFragment : BrowseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = fragmentAdapter

        GlobalScope.launch {

            prepareEntranceTransition()
            val boxOffice = NetworkManager.getBoxOffice()
            startEntranceTransition()

            val boxOfficeAdapter = ArrayObjectAdapter(MyPresenter())

            withContext(Dispatchers.Main) {
                boxOffice.forEach { movie ->
                    boxOfficeAdapter.add(movie)
                }

                fragmentAdapter.add(ListRow(HeaderItem(0, "Les sorties"), boxOfficeAdapter))
            }
        }

        GlobalScope.launch {
            val anticipateMovie = NetworkManager.getAnticipatedMovies()

            val anticipateMovieAdapter = ArrayObjectAdapter(MyPresenter())

            withContext(Dispatchers.Main) {
                anticipateMovie.forEach { movie ->
                    anticipateMovieAdapter.add(movie)
                }

                fragmentAdapter.add(ListRow(HeaderItem(1, "Les films à venir"), anticipateMovieAdapter))
            }
        }
    }

    override fun prepareEntranceTransition() {
        super.prepareEntranceTransition()
    }

    override fun startEntranceTransition() {
        super.startEntranceTransition()
    }
}