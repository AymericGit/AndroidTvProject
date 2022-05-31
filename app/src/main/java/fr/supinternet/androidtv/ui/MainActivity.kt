package fr.supinternet.androidtv.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import fr.supinternet.androidtv.R
import fr.supinternet.androidtv.data.network.model.Movie
import fr.supinternet.androidtv.fragment.MainFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to start Guided Fragment
        //val dialogIntent = Intent(this@MainActivity, DialogActivity::class.java)
        //startActivity(dialogIntent)

        //to notify user
        /*
        AlertDialog.Builder(this)
            .setTitle("Mon titre")
            .setMessage("Mon message")
            .setPositiveButton(android.R.string.ok, null)
            .show()
        */

        val fragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(android.R.id.content, fragment).commitAllowingStateLoss()
        fragment.setOnItemViewClickedListener(object: OnItemViewClickedListener {
            override fun onItemClicked(
                itemViewHolder: Presenter.ViewHolder?,
                item: Any?,
                rowViewHolder: RowPresenter.ViewHolder?,
                row: Row?
            ) {
                val movie : Movie = item as Movie
                val intent = Intent(this@MainActivity, DetailsActivity::class.java).apply {
                    putExtra("movie", movie)
                }
                startActivity(intent)
            }
        })
    }
}