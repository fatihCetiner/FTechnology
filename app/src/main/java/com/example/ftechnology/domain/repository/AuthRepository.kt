package com.example.ftechnology.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun signInUser(email: String, password: String)

    suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): SignUpResult

    fun logoutUser()
    fun getCurrentUser(): FirebaseUser?
}