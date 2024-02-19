package com.example.ftechnology.presentation.screen.settings

import android.app.AlertDialog
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.ftechnology.R
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val themeSwitch = findPreference<SwitchPreferenceCompat>("theme_switch")
        themeSwitch?.setOnPreferenceChangeListener { _, newValue ->
            val isDarkTheme = newValue as Boolean
            AppCompatDelegate.setDefaultNightMode(if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
            requireActivity().recreate()
            true
        }

        val languageList = findPreference<ListPreference>("language_list")
        languageList?.setOnPreferenceChangeListener { _, newValue ->
            val selectedLanguage = newValue as String
            setLocale(selectedLanguage)
            requireActivity().recreate()
            true
        }

    }

    private fun setLocale(languageCode: String) {
        val resources: Resources = resources
        val configuration: Configuration = resources.configuration
        val locale = Locale(languageCode)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        if (preference?.key == "logout_click") {
            showLogoutConfirmationDialog()
            return true
        }
        return super.onPreferenceTreeClick(preference)
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.log_out)
            .setMessage(R.string.exit_question)
            .setPositiveButton(R.string.yes) { dialog, which ->
                logout()
                redirectToLoginPage()
            }
            .setNegativeButton(R.string.no) { dialog, which ->
                Toast.makeText(requireContext(), "Logout canceled", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

    private fun redirectToLoginPage() {
        findNavController().navigate(R.id.settingsToSignIn)
    }

}