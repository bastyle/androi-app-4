package com.comp304.bastian.bastian.lab4.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.room.Room
import com.comp304.bastian.bastian.lab4.R
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityMainBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil
import com.comp304.bastian.bastian.lab4.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: MedicalDatabase
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("","onCreate...")

        //database = Room.databaseBuilder(applicationContext, MedicalDatabase::class.java, "MedicalCentre").fallbackToDestructiveMigration().build()
        database = MedicalDatabase.getInstance(baseContext)
        viewModel.initDatabase(database)


        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        Log.d("SharedPreferences", "isLoggedIn: $isLoggedIn")
        if(isLoggedIn){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            setContentView(binding.root)
            binding.loginButton.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("","restart...")
    }


}