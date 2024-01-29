package com.example.ftechnology.data.repository

import com.example.ftechnology.domain.repository.AuthRepository
import com.example.ftechnology.presentation.screen.signup.SignUpError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    private val _error = MutableSharedFlow<SignUpError>()
    val error: SharedFlow<SignUpError> get() = _error

    override fun signInUser(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun signUpUser(
        email: String,
        password: String,
        confirmPassword: String) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            error(SignUpError.FILL_IN_THE_BLANKS)
            return
        }
        if (password.length < 6 || confirmPassword.length < 6) {
            error(SignUpError.MIN_PASSWORD_LENGTH)
            return
        }
        if (password != confirmPassword) {
            error(SignUpError.DIFFERENT_PASSWORD)
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error(SignUpError.INVALID_EMAIL_ADDRESS)
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    override fun logoutUser() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}