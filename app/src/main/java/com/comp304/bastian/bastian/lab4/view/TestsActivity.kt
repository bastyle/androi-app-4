package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityTestsBinding
import com.comp304.bastian.bastian.lab4.viewmodel.TestsViewModel
import kotlinx.coroutines.launch

class TestsActivity(): AppCompatActivity() {
    private lateinit var binding: ActivityTestsBinding
    private lateinit var database: MedicalDatabase
    private lateinit var patientId : String
    private val viewModel: TestsViewModel  by viewModels()
    private lateinit var adapter: TestsActivityViewAdapter

    companion object{
        public const val ID_PATIENT_KEY = "ID_PATIENT_KEY"
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

        viewModel.getAllTestsByPatientId(1)

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



    }
}