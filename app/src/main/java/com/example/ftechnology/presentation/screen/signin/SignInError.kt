package com.example.ftechnology.presentation.screen.signin

import com.example.ftechnology.R
import com.example.ftechnology.presentation.screen.signup.SignUpError

enum class SignInError {
    FILL_IN_THE_BLANKS,
    MIN_PASSWORD_LENGTH,
    INVALID_EMAIL_ADDRESS,
    EMAIL_ALREADY_IN_USE,
    CHECK_INFORMATION;


    companion object {
        fun toStringResource(error: SignInError): Int {
            return when (error) {
                FILL_IN_THE_BLANKS -> R.string.fill_in_the_blanks
                MIN_PASSWORD_LENGTH -> R.string.password_insufficient
                INVALID_EMAIL_ADDRESS -> R.string.invalid_email
                EMAIL_ALREADY_IN_USE -> R.string.mail_is_in_use
                CHECK_INFORMATION -> R.string.check_information
            }
        }
    }
}