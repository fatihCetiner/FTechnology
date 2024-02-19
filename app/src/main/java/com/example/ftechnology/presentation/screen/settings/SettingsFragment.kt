package com.example.ftechnology.presentation.screen.settings

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.ftechnology.R
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
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