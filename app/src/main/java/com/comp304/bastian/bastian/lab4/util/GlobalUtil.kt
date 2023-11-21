package com.comp304.bastian.bastian.lab4.util

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.view.MainActivity

object GlobalUtil {

    private const val TAG= "GlobalUtil"
    private const val MY_PREFS="MyPrefs"
    public const val NURSE_ID_KEY="NurseId"

    fun addLoginStatus(context:Context, isLoggedIn:Boolean){
        val sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
        Log.d(TAG, "isLoggedIn: $isLoggedIn")
    }

    fun logout(context:Context, currentActivity: AppCompatActivity?){
        Log.d(TAG, "Logout")
        val sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false).apply()
        goToMainActivity(context, currentActivity)
        //val intent = Intent(context, MainActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //context.startActivity(intent)
    }

    fun login(context:Context, activity: AppCompatActivity?, userName: String){
        Log.d(TAG, "Login...")
        val sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", true).putString(NURSE_ID_KEY,userName).commit()
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        Log.d(TAG, "login isLoggedIn: $isLoggedIn")
        goToMainActivity(context, activity)
        //val intent = Intent(context, MainActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //context.startActivity(intent)
    }

    private fun goToMainActivity(context: Context, activity: AppCompatActivity?){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        activity?.finish()
    }

    fun getSharedPrefStr(context:Context, key:String): String? {
        val sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"Anonymous")
    }
}