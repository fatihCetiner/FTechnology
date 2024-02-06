package com.example.ftechnology.presentation.screen.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.ftechnology.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}