package com.comp304.bastian.bastian.lab4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.room.Room
import com.comp304.bastian.bastian.lab4.R
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityMainBinding
import com.comp304.bastian.bastian.lab4.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: MedicalDatabase
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(applicationContext, MedicalDatabase::class.java, "Nurses").build()
        viewModel.initDatabase(database)
        viewModel.defineDefaultNurses()
    }
}