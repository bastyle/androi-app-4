package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityHomeBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityLoginBinding
import com.comp304.bastian.bastian.lab4.viewmodel.MainActivityViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var database: MedicalDatabase
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        database = Room.databaseBuilder(applicationContext, MedicalDatabase::class.java, "MedicalCentre").fallbackToDestructiveMigration().build()
        viewModel.initDatabase(database)
        //load patients
        viewModel.getAllPatients()
    }
}