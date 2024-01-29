package com.example.ftechnology.presentation.screen.signup

import com.example.ftechnology.R

enum class SignUpError {
    FILL_IN_THE_BLANKS,
    MIN_PASSWORD_LENGTH,
    DIFFERENT_PASSWORD,
    INVALID_EMAIL_ADDRESS,
    EMAIL_ALREADY_IN_USE;

    companion object {
        fun toStringResource(error: SignUpError): Int {
            return when (error) {
                FILL_IN_THE_BLANKS -> R.string.fill_in_the_blanks
                MIN_PASSWORD_LENGTH -> R.string.password_insufficient
                DIFFERENT_PASSWORD -> R.string.password_is_not_the_same
                INVALID_EMAIL_ADDRESS -> R.string.invalid_email
                EMAIL_ALREADY_IN_USE -> R.string.mail_is_in_use
            }
        }
    }
}