package com.example.ftechnology.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun signInUser(email: String, password: String)
    fun signUpUser(email: String, password: String, confirmPassword: String)
    fun logoutUser()
    fun getCurrentUser(): FirebaseUser?
}