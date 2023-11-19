package com.comp304.bastian.bastian.lab4.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import com.comp304.bastian.bastian.lab4.databinding.ActivityLoginBinding
import com.comp304.bastian.bastian.lab4.util.ValidationUtils
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
        database = Room.databaseBuilder(applicationContext, MedicalDatabase::class.java, "MedicalCentre").fallbackToDestructiveMigration().build()
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

        viewModel.navigateToHome.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                // Ensure this activity is finished to prevent going back to it from HomeActivity
                finish()
                viewModel.onNavigationHandled()
            }
        }

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (ValidationUtils.isValidInput(username, password)) {
                // Perform login operation using ViewModel
                viewModel.login(username, password)

            } else {
                // Show an error message using the utility class
                ValidationUtils.showToast(this, "Invalid username or password")
            }
        }


    }
}