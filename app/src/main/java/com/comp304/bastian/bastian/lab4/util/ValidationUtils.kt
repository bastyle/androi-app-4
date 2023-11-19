package com.comp304.bastian.bastian.lab4.util

import android.content.Context
import android.widget.Toast

object ValidationUtils {

    fun isValidInput(username: String, password: String): Boolean {
        return username.isNotBlank() && password.isNotBlank()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}