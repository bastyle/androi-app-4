package com.comp304.bastian.bastian.lab4.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.databinding.ActivityTestsBinding
import com.comp304.bastian.bastian.lab4.viewmodel.TestsViewModel
import kotlinx.coroutines.launch

class TestsActivity(): AppCompatActivity() {
    private lateinit var binding: ActivityTestsBinding
    private lateinit var database: MedicalDatabase
    private lateinit var patientId : String
    private lateinit var patient : PatientEntity
    private val viewModel: TestsViewModel  by viewModels()
    private lateinit var adapter: TestsActivityViewAdapter

    companion object{
        public const val ID_PATIENT_KEY = "ID_PATIENT_KEY"
        public const val PATIENT_NAME_KEY = "PATIENT_NAME_KEY"
        private const val TAG = "TestsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = MedicalDatabase.getInstance(baseContext)//
        patientId = intent.getStringExtra(ID_PATIENT_KEY).toString()
        viewModel.setDatabase(database, patientId.toInt())

        adapter = TestsActivityViewAdapter(baseContext)

        //viewModel.getAllTestsByPatientId(1)

        binding.recyclerView.adapter=this.adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
        lifecycleScope.launch {
            viewModel.testsIdStateFlow.collect {
                adapter.updateList(it)
            }
        }
        //database.patientDao().getPatientById(patientId = patientId.toInt())
        //binding.patientInfo.text="Patient: "+database.patientDao().getPatientById(patientId = patientId.toInt()).firstName
        lifecycleScope.launch {
            viewModel.patient.observe(this@TestsActivity) { pat->
                binding.patientInfo.text= "Patient: "+ pat.firstName+" "+ pat.lastName
                patient = pat
            }
        }

        binding.addTestButton.setOnClickListener {
            //TODO add logic to add new test (new activity?) use activity_add_test.xml to create one

            val intent = Intent(this, AddTestActivity::class.java)
            intent.putExtra(ID_PATIENT_KEY, patientId)
            intent.putExtra(PATIENT_NAME_KEY, patient.firstName + " " +patient.lastName)
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }
}