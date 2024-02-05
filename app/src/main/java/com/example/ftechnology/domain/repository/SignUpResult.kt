package com.example.ftechnology.domain.repository

import com.example.ftechnology.presentation.screen.signup.SignUpError
import com.google.firebase.auth.AuthResult

data class SignUpResult(
    val result: AuthResult?,
    val error: SignUpError? = null
) {

    val isSuccess get() = result != null

    companion object {
        fun error(error: SignUpError) = SignUpResult(result = null, error = error)
    }
}