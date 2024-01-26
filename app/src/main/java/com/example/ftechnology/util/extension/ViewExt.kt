package com.example.ftechnology.util.extension

import android.graphics.Color
import android.view.View
import com.example.ftechnology.R
import com.google.android.material.snackbar.Snackbar

fun View.snack(message: String) {
    Snackbar.make(this, message, 1200)
        .setTextColor(resources.getColor(R.color.primary600))
        .setBackgroundTint(resources.getColor(R.color.secondary500))
        .show()
}