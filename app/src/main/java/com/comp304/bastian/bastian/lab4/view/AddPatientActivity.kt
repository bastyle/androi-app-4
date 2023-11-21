package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil
import com.comp304.bastian.bastian.lab4.viewmodel.AddPatientViewModel
import com.comp304.bastian.bastian.lab4.viewmodel.MainActivityViewModel

class AddPatientActivity():AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding
    private val viewModel: AddPatientViewModel by viewModels()

    companion object{
        const val TAG ="AddPatientActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG,"oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setDatabase(MedicalDatabase.getInstance(baseContext))
        val username = GlobalUtil.getSharedPrefStr(this,GlobalUtil.NURSE_ID_KEY) as String
        Log.d(TAG, "nurseId: "+username)
        if (username != null) {
            Log.d(TAG, "nurseId database: "+viewModel.getNurseById(username))
            binding.editTextNurseId.setText(username)
        }



        binding.buttonSavePatient.setOnClickListener {
            Log.d(TAG,"save...")
        }
    }
}