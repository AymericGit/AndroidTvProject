package fr.supinternet.androidtv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import fr.supinternet.androidtv.R
import fr.supinternet.androidtv.data.network.model.Movie
import fr.supinternet.androidtv.fragment.DetailsFragment

class DetailsActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val movie : Movie? = intent.getParcelableExtra("movie")
        movie?.let {
            Log.d("PRINT", it.name.toString())
            supportFragmentManager.beginTransaction().replace(android.R.id.content, launchDetails(it)).commitAllowingStateLoss()
        }
    }

    private fun launchDetails(movie : Movie): DetailsFragment {
        val detailFrag = DetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("movie", movie);
        detailFrag.arguments = bundle;
        return detailFrag;
    }
}

