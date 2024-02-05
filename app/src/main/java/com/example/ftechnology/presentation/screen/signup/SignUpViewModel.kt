package com.example.ftechnology.presentation.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ftechnology.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _navigateToSignInScreen = MutableSharedFlow<Unit>()
    val navigateToSignInScreen: SharedFlow<Unit> get() = _navigateToSignInScreen

    private val _error = MutableSharedFlow<SignUpError>()
    val error: SharedFlow<SignUpError> get() = _error

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val signUpResult = repository.signUp(
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
            if (signUpResult.isSuccess) {
                navigateToSignInScreen()
            } else {
                sendError(signUpResult.error)
            }
        }
    }

    private fun sendError(error: SignUpError?) {
        error ?: return
        viewModelScope.launch {
            _error.emit(error)
        }
    }

    private fun navigateToSignInScreen() {
        viewModelScope.launch {
            _navigateToSignInScreen.emit(Unit)
        }
    }
}