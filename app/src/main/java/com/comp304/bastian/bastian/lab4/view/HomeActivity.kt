package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.databinding.ActivityHomeBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}