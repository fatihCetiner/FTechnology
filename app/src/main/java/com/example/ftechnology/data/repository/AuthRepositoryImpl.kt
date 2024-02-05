package com.example.ftechnology.data.repository

import com.example.ftechnology.domain.repository.AuthRepository
import com.example.ftechnology.domain.repository.SignInResult
import com.example.ftechnology.domain.repository.SignUpResult
import com.example.ftechnology.presentation.screen.signin.SignInError
import com.example.ftechnology.presentation.screen.signup.SignUpError
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

    override suspend fun signIn(
        email: String,
        password: String
    ): SignInResult  = suspendCoroutine { cont ->
        if (email.isEmpty() || password.isEmpty()) {
            cont.resume(SignInResult.error(SignInError.FILL_IN_THE_BLANKS))
        }
        if (password.length < 6) {
            cont.resume(SignInResult.error(SignInError.MIN_PASSWORD_LENGTH))
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            cont.resume(SignInResult.error(SignInError.INVALID_EMAIL_ADDRESS))
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    cont.resume(SignInResult(authResult))
                } else {
                    cont.resume(SignInResult.error(SignInError.CHECK_INFORMATION))
                }
            }
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