package com.example.ftechnology.domain.repository

import com.example.ftechnology.presentation.screen.signin.SignInError
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

data class SignInResult(
    val result: Task<AuthResult>?,
    val error: SignInError? = null
) {

    val isSuccess get() = result != null

    companion object {
        fun error(error: SignInError) = SignInResult(result = null, error = error)
    }
}