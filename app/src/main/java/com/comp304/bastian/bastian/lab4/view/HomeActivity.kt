package com.comp304.bastian.bastian.lab4.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.databinding.ActivityHomeBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientsBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("HomeActivity", "oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textNurseId.text="Nurse: "+ (GlobalUtil.getSharedPrefStr(this, GlobalUtil.NURSE_ID_KEY)
            ?.uppercase() ?: "")

        binding.buttonToPatients.setOnClickListener {
            val intent = Intent(this, PatientsActivity::class.java)
            startActivity(intent)
        }
        binding.buttonToTests.setOnClickListener {
            val intent = Intent(this, TestsActivity::class.java)
            startActivity(intent)
        }

    }
}