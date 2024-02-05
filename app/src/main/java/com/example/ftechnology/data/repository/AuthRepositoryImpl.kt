package com.example.ftechnology.data.repository

import com.example.ftechnology.domain.repository.AuthRepository
import com.example.ftechnology.domain.repository.SignUpResult
import com.example.ftechnology.presentation.screen.signup.SignUpError
import com.example.ftechnology.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    private val _error = MutableSharedFlow<SignUpError>()
    val error: SharedFlow<SignUpError> get() = _error

    override fun signInUser(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): SignUpResult = suspendCoroutine { cont ->
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            cont.resume(SignUpResult.error(SignUpError.FILL_IN_THE_BLANKS))
        }
        if (password.length < 6 || confirmPassword.length < 6) {
            cont.resume(SignUpResult.error(SignUpError.MIN_PASSWORD_LENGTH))

        }
        if (password != confirmPassword) {
            cont.resume(SignUpResult.error(SignUpError.DIFFERENT_PASSWORD))

        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            cont.resume(SignUpResult.error(SignUpError.INVALID_EMAIL_ADDRESS))
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                cont.resume(SignUpResult(authResult))
            }.addOnFailureListener {
                cont.resume(SignUpResult.error(SignUpError.UNKNOWN))
            }
    }

    override fun logoutUser() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}