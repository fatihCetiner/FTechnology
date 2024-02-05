package com.example.ftechnology.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signIn(
        email: String,
        password: String
    ): SignInResult

    suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): SignUpResult

    fun logoutUser()
    fun getCurrentUser(): FirebaseUser?
}