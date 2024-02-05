package com.example.ftechnology.presentation.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ftechnology.domain.repository.AuthRepository
import com.example.ftechnology.presentation.screen.signup.SignUpError
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _navigateToProductScreen = MutableSharedFlow<Unit>()
    val navigateToSignInScreen: SharedFlow<Unit> get() = _navigateToProductScreen

    private val _error = MutableSharedFlow<SignInError>()
    val error: SharedFlow<SignInError> get() = _error

    fun checkUserInfo(){
        val currentUser = repository.getCurrentUser()
        if (currentUser != null){
            navigateProductScreen()
        }
    }

    fun signIn(
        email: String,
        password: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val signInResult = repository.signIn(
                email = email,
                password = password
            )
            if (signInResult.isSuccess) {
                navigateProductScreen()
            } else {
                sendError(signInResult.error)
            }
        }
    }

    private fun sendError(error: SignInError?) {
        error ?: return
        viewModelScope.launch {
            _error.emit(error)
        }
    }

    private fun navigateProductScreen() {
        viewModelScope.launch {
            _navigateToProductScreen.emit(Unit)
        }
    }
}


