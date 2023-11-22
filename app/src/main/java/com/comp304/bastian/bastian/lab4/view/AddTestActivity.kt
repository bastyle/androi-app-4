package com.comp304.bastian.bastian.lab4.view

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityAddTestBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil
import com.comp304.bastian.bastian.lab4.viewmodel.AddPatientViewModel
import com.comp304.bastian.bastian.lab4.viewmodel.AddTestViewModel
import kotlinx.coroutines.launch

class AddTestActivity():AppCompatActivity() {
    private lateinit var binding: ActivityAddTestBinding
    private lateinit var database: MedicalDatabase
    private val viewModel: AddTestViewModel by viewModels()
    private lateinit var patientId : String
    private lateinit var patientName : String

    private lateinit var nurseList: List<String>
    private lateinit var selectedNurseId: String
    private lateinit var nursedIdsadapter: ArrayAdapter<String>
    companion object{
        const val TAG ="AddTestActivity"
        const val MIN_LENGTH = 5
        const val MAX_LENGTH = 12
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setDatabase(MedicalDatabase.getInstance(baseContext))
        patientId = intent.getStringExtra(TestsActivity.ID_PATIENT_KEY).toString()
        patientName = intent.getStringExtra(TestsActivity.PATIENT_NAME_KEY).toString()
        binding.editTextPatientId.setText(patientName+" ID: "+patientId)


        lifecycleScope.launch {
            viewModel.nursesIdStateFlow.collect {
                nurseList = it
                loadNurses()
            }
        }

        binding.buttonSaveTest.setOnClickListener {
            Log.d(TAG,"save...")
            saveTest()
        }
    }

    private fun saveTest() {
        // Retrieve test details from UI
        val patientId = patientId as Int //binding.editTextPatientId.text.toString().trim().toInt()
        //val nurseId = binding.editTextNurseId.text.toString().trim()
        val department = binding.editTextDepartment.text.toString().trim()
        val bpl = binding.editTextBPL.text.toString().trim().toFloat()
        val bph = binding.editTextBPH.text.toString().trim().toFloat()
        val temperature = binding.editTextTemperature.text.toString().trim().toFloat()

        // Validate inputs and save the test
       /* if (validateInputs(patientId, nurseId, department, bpl, bph, temperature)) {
            val newTest = Test(
                patientId = patientId,
                nurseId = nurseId,
                department = department,
                BPL = bpl,
                BPH = bph,
                temperature = temperature
            )

            // Save the test using the repository or your preferred method
            // testViewModel.saveTest(newTest)

            // Show a success message using Toast (similar to previous examples)

            // Finish the current activity and go back to the previous activity
            finish()
        }*/
    }


    private fun validateInputs(firstName: String, lastName: String, nurseId: String, room: String): Boolean {
        // Implement your validation logic here (e.g., check for empty fields, length, format, etc.)
        if (firstName.isEmpty() || lastName.isEmpty() || nurseId.isEmpty() || room.isEmpty()) {
            // Display an error message or handle validation failure
            Toast.makeText(this, "Please complete all fields!", Toast.LENGTH_SHORT).show()
            return false
        }else if( (firstName.length< AddPatientActivity.MIN_LENGTH || firstName.length> AddPatientActivity.MAX_LENGTH) || (lastName.length< AddPatientActivity.MIN_LENGTH || lastName.length> AddPatientActivity.MAX_LENGTH) ){
            Toast.makeText(this, "Names must be at least ${AddPatientActivity.MIN_LENGTH} characters and maximum ${AddPatientActivity.MAX_LENGTH}.", Toast.LENGTH_SHORT).show()
            return false
        }else if(!firstName.matches(Regex("[a-zA-Z ]+")) || !lastName.matches(Regex("[a-zA-Z ]+"))){
            Toast.makeText(this, "Names only accept alphabetical characters.", Toast.LENGTH_SHORT).show()
            return false
        }else if(!room.matches(Regex("\\d{2,4}"))){
            Toast.makeText(this, "Room must be a number between 10 and maximum 9999.", Toast.LENGTH_SHORT).show()
            return false
        }else if(!room.matches(Regex("-?\\d+(\\.\\d+)?"))){
            Toast.makeText(this, "Room just accept Numbers.", Toast.LENGTH_SHORT).show()
            return false
        }


        return true
    }


    private fun loadNurses() {
        nursedIdsadapter = ArrayAdapter(this, R.layout.simple_spinner_item, nurseList)
        nursedIdsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNurseId.adapter = nursedIdsadapter
        binding.spinnerNurseId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                selectedNurseId = nurseList[position]
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }
        val username = GlobalUtil.getSharedPrefStr(this, GlobalUtil.NURSE_ID_KEY) as String
        Log.d(AddPatientActivity.TAG, "nurseId: "+username)
        if (username != null) {
            selectedNurseId = username
            binding.spinnerNurseId.setSelection(nurseList.indexOf(selectedNurseId))
        }
    }

}