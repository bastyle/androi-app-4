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
import com.comp304.bastian.bastian.lab4.database.TestEntity
import com.comp304.bastian.bastian.lab4.databinding.ActivityAddTestBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil
import com.comp304.bastian.bastian.lab4.util.ValidationUtils
import com.comp304.bastian.bastian.lab4.viewmodel.AddTestViewModel
import kotlinx.coroutines.launch

class AddTestActivity():AppCompatActivity() {
    private lateinit var binding: ActivityAddTestBinding
    private lateinit var database: MedicalDatabase
    private val viewModel: AddTestViewModel by viewModels()
    private var patientId = "0"
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
        val department = binding.editTextDepartment.text.toString().trim()
        val bpl = binding.editTextBPL.text.toString().trim()
        val bph = binding.editTextBPH.text.toString().trim()
        val temperature = binding.editTextTemperature.text.toString().trim()
        // Validate inputs and save the test
        if (validateInputs(department, bpl, bph, temperature)) {
            val newTest = TestEntity(
                patientId = patientId.toInt(),
                nurseId = selectedNurseId,
                department = department,
                BPL = bpl.toFloat(),
                BPH = bph.toFloat(),
                temperature = temperature.toFloat()
            )
            viewModel.saveNewTest(newTest)
            Toast.makeText(this, "Test added successfully!", Toast.LENGTH_LONG).show()
            finish()
        }
    }


    private fun validateInputs(department: String, bpl: String, bph: String, temperature: String): Boolean {
        if (department.isEmpty() || bpl.isEmpty() || bph.isEmpty() || temperature.isEmpty()) {
            Toast.makeText(this, "Please complete all fields!", Toast.LENGTH_SHORT).show()
            return false
        } else if(!ValidationUtils.isAlphabetical(department)){
            Toast.makeText(this, "Department only accept alphabetical characters.", Toast.LENGTH_SHORT).show()
            return false
        }else if( (!ValidationUtils.isValidLength(department)) ){
            Toast.makeText(this, "Department must be at least ${ValidationUtils.MIN_LENGTH} and maximum ${ValidationUtils.MAX_LENGTH} characters.", Toast.LENGTH_SHORT).show()
            return false
        }else if(!ValidationUtils.isFloatNumber(bph) || !ValidationUtils.isFloatNumber(bpl) || !ValidationUtils.isFloatNumber(temperature)){
            Toast.makeText(this, "BPL,BPH, and Temperature must be a float number.", Toast.LENGTH_SHORT).show()
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
        if (username != null) {
            selectedNurseId = username
            binding.spinnerNurseId.setSelection(nurseList.indexOf(selectedNurseId))
        }
    }

}