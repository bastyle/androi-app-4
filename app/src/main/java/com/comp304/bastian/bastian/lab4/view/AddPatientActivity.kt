package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.PatientEntity
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
            savePatient()
        }
    }

    private fun savePatient() {
        val firstName = binding.editTextFirstName.text.toString().trim()
        val lastName = binding.editTextLastName.text.toString().trim()
        val nurseId = binding.editTextNurseId.text.toString().trim()
        val room = binding.editTextRoom.text.toString().trim()

        if (validateInputs(firstName, lastName, nurseId, room)) {
            val newPatient = PatientEntity(
                firstName = firstName,
                lastName = lastName,
                nurseId = nurseId,
                room = room
            )
            viewModel.saveNewPatient(newPatient)

            // Optionally, navigate to another activity or perform other actions
        }
    }

    private fun validateInputs(firstName: String, lastName: String, nurseId: String, room: String): Boolean {
        // Implement your validation logic here (e.g., check for empty fields, length, format, etc.)
        if (firstName.isEmpty() || lastName.isEmpty() || nurseId.isEmpty() || room.isEmpty()) {
            // Display an error message or handle validation failure
            return false
        }

        // Add more validation checks as needed

        return true
    }
}