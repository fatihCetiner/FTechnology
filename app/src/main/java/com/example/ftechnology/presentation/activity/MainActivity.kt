package com.example.ftechnology.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.ftechnology.R
import com.example.ftechnology.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
}