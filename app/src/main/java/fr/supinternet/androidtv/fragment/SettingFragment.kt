package fr.supinternet.androidtv.fragment

import androidx.leanback.preference.LeanbackSettingsFragmentCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen

class SettingFragment : LeanbackSettingsFragmentCompat() {
    override fun onPreferenceStartInitialScreen() {
        startPreferenceFragment(PreferenceFragment())
    }

    override fun onPreferenceStartScreen(caller: PreferenceFragmentCompat?, pref: PreferenceScreen? ): Boolean {
        return true
    }

    override fun onPreferenceStartFragment( caller: PreferenceFragmentCompat?, pref: Preference? ): Boolean {
        return true
    }
}