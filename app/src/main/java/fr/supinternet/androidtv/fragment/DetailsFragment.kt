package fr.supinternet.androidtv.fragment

import android.os.Bundle
import androidx.leanback.app.DetailsSupportFragment
import fr.supinternet.androidtv.data.network.model.Movie

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.util.DisplayMetrics
import android.util.Log
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import androidx.leanback.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.supinternet.androidtv.presenter.DetailsPresenter
import fr.supinternet.androidtv.ui.DetailsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailsFragment : DetailsSupportFragment() {
    private var movie: Movie? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            movie = bundle.getParcelable<Parcelable>("movie") as Movie?
            Log.d("PRINTED", movie?.name.toString())
        }

        val detailRow = DetailsOverviewRow(movie)
        val detailsPresenter = FullWidthDetailsOverviewRowPresenter(
            DetailsPresenter()
        )

        GlobalScope.launch {
            detailsPresenter.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.background_dark))
            detailsPresenter.setInitialState(FullWidthDetailsOverviewRowPresenter.STATE_FULL)
            val ps = ClassPresenterSelector()
            ps.addClassPresenter(DetailsOverviewRow::class.java, detailsPresenter)
            ps.addClassPresenter(ListRow::class.java, ListRowPresenter())

            adapter = ArrayObjectAdapter(ps).apply { add(detailRow) }
            val sparseArrayObjectAdapter = SparseArrayObjectAdapter()
            sparseArrayObjectAdapter.set(1, Action(1, "Voir le trailer"))
            detailRow.actionsAdapter = sparseArrayObjectAdapter

            detailsPresenter.onActionClickedListener = OnActionClickedListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie?.trailerURL))
                startActivity(browserIntent)
            }
        }

        GlobalScope.launch {
            val bitmap = Glide.with(requireActivity())
                .asBitmap()
                .load(movie?.posterURL)
                .apply(RequestOptions().apply { centerCrop() })
                .submit(250, 250)
                .get()
            detailRow.setImageBitmap(requireContext(), bitmap)
        }

        GlobalScope.launch {
            movie?.posterURL?.let { loadBackgroundImage(it) }
        }
    }

    @UiThread
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun loadBackgroundImage(url: String) {
        val metrics  = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics )

        val drawable = Glide.with(requireActivity())
            .asDrawable()
            .load(url)
            .apply(RequestOptions().apply { centerCrop() })
            .submit()
            .get()

        withContext(Dispatchers.Main) {
            requireActivity().window.decorView.background = drawable
        }
    }

}