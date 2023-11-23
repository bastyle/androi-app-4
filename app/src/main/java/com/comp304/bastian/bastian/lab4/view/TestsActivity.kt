package com.comp304.bastian.bastian.lab4.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.comp304.bastian.bastian.lab4.R
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
    private var patientName = String()
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
        patientName = intent.getStringExtra(GlobalUtil.PATIENT_NAME_KEY).toString()

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
        setSubtitle()


    }

    private fun setSubtitle(){
        if(patientName.isNullOrBlank() || "null"==patientName){
            binding.textInfo.text="Tests:"
        }else {
            binding.textInfo.text="Patient "+patientName+" tests:"
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")


        if(!patientId.isNullOrBlank() && "null" != patientId){
            viewModel.getAllTestsByPatientId(patientId.toInt())
        }else{
            viewModel.getAllTests()
        }
        setSubtitle()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG,"onCreateOptionsMenu..........")
        menuInflater.inflate(com.comp304.bastian.bastian.lab4.R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.comp304.bastian.bastian.lab4.R.id.menu_logout -> {
                GlobalUtil.logout(baseContext,this)
                return true
            }
            R.id.home->{

                val intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}