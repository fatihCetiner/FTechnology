package com.example.ftechnology.presentation.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SplashViewModel: ViewModel() {

    private val _navigateToOnboarding = MutableLiveData<Unit>()
    val navigateToOnboarding: LiveData<Unit> get() = _navigateToOnboarding
    init {
        startSplash()
    }

    private fun startSplash() {
        viewModelScope.launch {
            delay(4.seconds)
            _navigateToOnboarding.value = Unit
        }
    }
}