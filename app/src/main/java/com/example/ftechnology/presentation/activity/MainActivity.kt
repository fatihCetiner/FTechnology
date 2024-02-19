package com.example.ftechnology.presentation.activity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.example.ftechnology.R
import com.example.ftechnology.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        applyTheme()
        applyLanguage()

        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> binding.bottomNavigation.isGone = true
                R.id.viewPagerFragment -> binding.bottomNavigation.isGone = true
                R.id.signInFragment -> binding.bottomNavigation.isGone = true
                R.id.singUpFragment -> binding.bottomNavigation.isGone = true
                R.id.detailsFragment -> binding.bottomNavigation.isGone = true
                R.id.productFragment -> binding.bottomNavigation.isVisible = true
                R.id.favoriteFragment -> binding.bottomNavigation.isVisible = true
                R.id.cartFragment -> binding.bottomNavigation.isVisible = true
            }
        }
    }

    private fun applyTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isDarkTheme = sharedPreferences.getBoolean("theme_key", false)
        AppCompatDelegate.setDefaultNightMode(if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun applyLanguage() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val languageCode = sharedPreferences.getString("language_key", "en")
        val locale = Locale(languageCode!!)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}