package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.databinding.ActivityLoginBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Implement your login logic here
    }
}