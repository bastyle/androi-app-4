package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientBinding

class AddPatientActivity:AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding

    companion object{
        const val TAG ="AddPatientActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG,"oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}