package com.comp304.bastian.bastian.lab4.util

import android.content.Context
import android.text.BoringLayout
import android.widget.Toast

object ValidationUtils {
    //private val floatPattern = Regex("^[-+]?[0-9]*\\.[0-9]+([eE][-+]?[0-9]+)?$")
    val floatPattern = Regex("^([0-9]+)?[.][0-9]+?$")

    public const val MIN_LENGTH = 3
    public const val MAX_LENGTH = 12
    fun isValidInput(username: String, password: String): Boolean {
        return username.isNotBlank() && password.isNotBlank()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun isFloatNumber(number:String):Boolean{
        return floatPattern.matches(number)
    }

    fun isValidLength(input:String):Boolean{
        return (input.length in MIN_LENGTH..MAX_LENGTH)
    }

    fun isAlphabetical(input:String):Boolean{
        return input.matches(Regex("[a-zA-Z ]+"))
    }
}