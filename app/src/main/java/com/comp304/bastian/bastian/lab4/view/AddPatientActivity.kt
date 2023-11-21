package com.comp304.bastian.bastian.lab4.view

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil
import com.comp304.bastian.bastian.lab4.viewmodel.AddPatientViewModel
import com.comp304.bastian.bastian.lab4.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

class AddPatientActivity():AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding
    private val viewModel: AddPatientViewModel by viewModels()

    private lateinit var nurseList: List<String>
    private lateinit var selectedNurseId: String

    companion object{
        const val TAG ="AddPatientActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG,"oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setDatabase(MedicalDatabase.getInstance(baseContext))

        lifecycleScope.launch {
            viewModel.nursesIdStateFlow.collect {
                //adapter.updateList(it)
                nurseList = it
            }
        }

        loadNurseIds()
        binding.buttonSavePatient.setOnClickListener {
            Log.d(TAG,"save...")
            savePatient()
        }
    }

    private fun loadNurseIds() {
        // Replace this with the actual logic to load nurse IDs from the database
        // For demonstration purposes, nurseList is populated with dummy data
        //viewModel.getNurseIds()
        //nurseList.clear()
        //nurseList.addAll()

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, nurseList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNurseId.adapter = adapter

        // Set a listener to capture the selected nurse ID
        binding.spinnerNurseId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                selectedNurseId = nurseList[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }
        val username = GlobalUtil.getSharedPrefStr(this,GlobalUtil.NURSE_ID_KEY) as String
        Log.d(TAG, "nurseId: "+username)
        if (username != null) {
            selectedNurseId = username
            binding.spinnerNurseId.setSelection(nurseList.indexOf(selectedNurseId))
        }
    }

    private fun savePatient() {
        val firstName = binding.editTextFirstName.text.toString().trim()
        val lastName = binding.editTextLastName.text.toString().trim()

        //val nurseId = binding.editTextNurseId.text.toString().trim()
        //val nurseId = selectedNurse
        val room = binding.editTextRoom.text.toString().trim()

        if (validateInputs(firstName, lastName, selectedNurseId, room)) {
            val newPatient = PatientEntity(
                firstName = firstName,
                lastName = lastName,
                nurseId = selectedNurseId,
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