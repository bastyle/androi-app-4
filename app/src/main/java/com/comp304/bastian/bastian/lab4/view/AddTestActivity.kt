package com.comp304.bastian.bastian.lab4.view

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.databinding.ActivityAddTestBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientBinding
import com.comp304.bastian.bastian.lab4.viewmodel.AddPatientViewModel

class AddTestActivity():AppCompatActivity() {
    private lateinit var binding: ActivityAddTestBinding
    private val viewModel: AddPatientViewModel by viewModels()



}