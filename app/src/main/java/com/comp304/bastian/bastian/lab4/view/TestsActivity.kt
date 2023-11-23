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
import com.comp304.bastian.bastian.lab4.util.GlobalUtil
import com.comp304.bastian.bastian.lab4.viewmodel.TestsViewModel
import kotlinx.coroutines.launch

class TestsActivity(): AppCompatActivity() {
    private lateinit var binding: ActivityTestsBinding
    private lateinit var database: MedicalDatabase
    private var patientId = String()
    private lateinit var patient : PatientEntity
    private val viewModel: TestsViewModel  by viewModels()
    private lateinit var adapter: TestsActivityViewAdapter

    companion object{
        private const val TAG = "TestsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = MedicalDatabase.getInstance(baseContext)//
        patientId = intent.getStringExtra(GlobalUtil.ID_PATIENT_KEY).toString()

        if(patientId.isNullOrBlank() || "null" == patientId){
            viewModel.setDatabase(database)
        }else  {
            viewModel.setDatabase(database, patientId.toInt())
        }
        adapter = TestsActivityViewAdapter(baseContext)


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

       /* lifecycleScope.launch {
            viewModel.patient.observe(this@TestsActivity) { pat->
                binding.patientInfo.text= "Patient: "+ pat.firstName+" "+ pat.lastName
                patient = pat
            }
        }*/
        binding.patientInfo.text="Nurse: "+ (GlobalUtil.getSharedPrefStr(this, GlobalUtil.NURSE_ID_KEY)
            ?.uppercase() ?: "")

        binding.addTestButton.setOnClickListener {
            val intent = Intent(this, AddTestActivity::class.java)
            intent.putExtra(GlobalUtil.ID_PATIENT_KEY, patientId)
            //intent.putExtra(GlobalUtil.PATIENT_NAME_KEY, patient.firstName + " " +patient.lastName)
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")
        viewModel.getAllTestsByPatientId(patientId.toInt())
    }
}