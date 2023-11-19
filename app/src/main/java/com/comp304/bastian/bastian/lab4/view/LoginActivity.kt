package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import com.comp304.bastian.bastian.lab4.databinding.ActivityLoginBinding
import com.comp304.bastian.bastian.lab4.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

//class LoginActivity(private val mainActivityViewModel: MainActivityViewModel) : AppCompatActivity() {
class LoginActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: MedicalDatabase
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Room.databaseBuilder(applicationContext, MedicalDatabase::class.java, "MedicalCentre").build()
        viewModel.initDatabase(database)
        // Implement your login logic here
        viewModel.getAllNurses()

        lifecycleScope.launch {
            viewModel.nurseStateFlow.collect {
                //adapter.updateAdapter(it)
                it.forEach{nurse->
                    Log.e("Login", nurse.firstName)
                }
            }
        }


    }
}