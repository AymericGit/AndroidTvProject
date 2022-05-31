package fr.supinternet.androidtv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.GuidedStepSupportFragment
import fr.supinternet.androidtv.R
import fr.supinternet.androidtv.fragment.GuidedFragment

class DialogActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        GuidedStepSupportFragment.addAsRoot(this, GuidedFragment(), android.R.id.content);
    }
}